package com.jdk2010.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdk2010.framework.dal.model.Model;

/**
 * ModelInjector
 */
public class ModelInjector {

    private static Logger logger = LoggerFactory.getLogger(ModelInjector.class);

    @SuppressWarnings("unchecked")
    public static <T> T inject(Class<?> modelClass, HttpServletRequest request, boolean skipConvertError)
            throws Exception {
        String modelName = modelClass.getSimpleName();
        return (T) inject(modelClass, DbKit.firstCharToLowerCase(modelName), request, skipConvertError);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static final <T> T inject(Class<?> modelClass, String modelName, HttpServletRequest request,
            boolean skipConvertError) throws Exception {
        Object model = null;
        try {
            model = modelClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (model instanceof Model)
            injectActiveRecordModel((Model) model, modelName, request, skipConvertError);
        else
            injectCommonModel(model, modelName, request, modelClass, skipConvertError);

        return (T) model;
    }

    private static final void injectCommonModel(Object model, String modelName, HttpServletRequest request,
            Class<?> modelClass, boolean skipConvertError) {
        Method[] methods = modelClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set") == false) // only setter method
                continue;

            Class<?>[] types = method.getParameterTypes();
            if (types.length != 1) // only one parameter
                continue;
            String attrName = methodName.substring(3);
            String value = request.getParameter(modelName + "." + DbKit.firstCharToLowerCase(attrName));
            if (value != null) {
                try {
                    method.invoke(model, TypeConverter.convert(types[0], value));
                } catch (Exception e) {
                    if (skipConvertError == false)
                        throw new RuntimeException(e);
                }
            }
        }
    }

    private static final void injectActiveRecordModel(Model<?> model, String modelName, HttpServletRequest request,
            boolean skipConvertError) throws Exception {
        String modelNameAndDot = modelName + ".";
        Map<String, String[]> parasMap = request.getParameterMap();
        for (Entry<String, String[]> e : parasMap.entrySet()) {
            String paraKey = e.getKey();

            if (paraKey.startsWith(modelNameAndDot)) {
                String paraName = paraKey.substring(modelNameAndDot.length());
                Class colType = getFieldClass(model, paraName);
                if (colType == null)
                    throw new RuntimeException("The model attribute " + paraKey + " is not exists.");
                String[] paraValue = e.getValue();
                try {
                    Object value = paraValue[0] != null ? TypeConverter.convert(colType, paraValue[0]) : null;
                    DbKit.setPropertieValue(paraName, model, value);
                } catch (Exception ex) {
                    if (skipConvertError == false)
                        throw new RuntimeException("Can not convert parameter: " + modelNameAndDot + paraName, ex);
                }
            }
        }
    }

    private static final Class getFieldClass(Model<?> model, String inField) throws Exception {
        Class clazz = null;
        Field field = null;
        try {
            field = model.getClass().getDeclaredField(inField);
        } catch (Exception e) {
            clazz = null;
            logger.info("请检查字段:" + inField + "在：model" + model.getClass().getName() + "是否存在");
        }
        clazz = field.getType();
        return clazz;
    }
}
