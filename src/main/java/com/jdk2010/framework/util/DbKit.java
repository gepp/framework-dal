package com.jdk2010.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jdk2010.framework.dal.client.support.router.BaseRouterStrategy;
import com.jdk2010.framework.dal.client.support.router.RouterManager;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.dal.parse.annotation.TableField;
import com.jdk2010.framework.dal.parse.annotation.TableRouterRule;

public class DbKit {

    private Map<String, Object> params;

    // 缓存所有的字段
    private static Map<String, Set<String>> allFieldmap = new ConcurrentHashMap<String, Set<String>>();
    // 缓存 所有的数据库字段
    private static Map<String, List<String>> allDBFieldmap = new ConcurrentHashMap<String, List<String>>();

    private StringBuffer sql = new StringBuffer();

    private String orderSql = null;

    public String getOrderSql() {
        return orderSql;
    }

    public void setOrderSql(String orderSql) {
        this.orderSql = orderSql;
    }

    public DbKit(String s) {
        this.sql.append(s);
    }

    public DbKit append(String s) {
        sql.append(" " + s);
        return this;
    }

    public DbKit put(String param, Object value) {
        if (params == null)
            params = new HashMap<String, Object>();
        params.put(param, value);
        return this;
    }

    public Object get(String key) {
        if (params == null)
            params = new HashMap<String, Object>();
        return params.get(key);
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public String getParamsToString() {
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (String key : params.keySet()) {
                sb.append(key + ":" + params.get(key) + "\r\n");
            }
        }
        return sb.toString();
    }

    public String getSql() {
        if (sql == null)
            return null;
        else
            return sql.toString();
    }

    public static List<String> getAllDBFields(Class clazz) {
        if (clazz == null) {
            return null;
        }
        String className = clazz.getName();
        boolean iskey = allDBFieldmap.containsKey(className);
        if (iskey) {
            return allDBFieldmap.get(className);
        }

        Set<String> allNames = getAllFieldNames(clazz);
        if (allNames == null || allNames.isEmpty()) {
            return null;
        }
        List<String> dbList = new ArrayList<String>();
        for (String fdName : allNames) {
            boolean isDB = isAnnotation(clazz, fdName, TableField.class);
            if (isDB == true) {
                dbList.add(fdName);
            }
        }
        allDBFieldmap.put(className, dbList);
        return dbList;
    }

    public static Set<String> getAllFieldNames(Class clazz) {
        if (clazz == null) {
            return null;
        }
        String className = clazz.getName();
        boolean iskey = allFieldmap.containsKey(className);
        if (iskey) {
            return allFieldmap.get(className);
        }
        Set<String> allSet = new HashSet<String>();
        allSet = recursionFiled(clazz, allSet);
        allFieldmap.put(className, allSet);
        return allSet;
    }

    /**
     * 递归查询父类的所有属性,set 去掉重复的属性
     * 
     * @param clazz
     * @param fdNameSet
     * @return
     * @throws Exception
     */
    private static Set<String> recursionFiled(Class clazz, Set<String> fdNameSet) {
        Field[] fds = clazz.getDeclaredFields();
        String name = "";
        for (int i = 0; i < fds.length; i++) {
            Field fd = fds[i];
            name = (fd.getName());
            fdNameSet.add(name);
        }
        return fdNameSet;
    }

    public static boolean isAnnotation(Class clazz, String fdName, Class annotationClass) {
        PropertyDescriptor pd = null;
        Method getMethod = null;
        try {
            if (clazz == null || fdName == null || annotationClass == null)
                return false;
            pd = new PropertyDescriptor(fdName, clazz);
            getMethod = pd.getReadMethod();// 获得get方法
            return getMethod.isAnnotationPresent(annotationClass);
        } catch (Exception e) {
            ExceptionUtil.throwException(e);
        }
        return false;
    }
    
    public static boolean isAnnotationField(Class clazz, String fdName, Class annotationClass) {
        PropertyDescriptor pd = null;
        Method getMethod = null;
        try {
            if (clazz == null || fdName == null || annotationClass == null)
                return false;
            
            pd = new PropertyDescriptor(fdName, clazz);
            Field field=clazz.getDeclaredField(fdName);
            Annotation anno=field.getAnnotation(annotationClass);
            if(anno!=null){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            ExceptionUtil.throwException(e);
        }
        return false;
    }

    public static String getBaseTableNameByClass(Class clazz) {
        if (clazz == null)
            return null;
        String tableName = "";
        String simpleName = clazz.getSimpleName();
        for (int i = 0; i < simpleName.length(); i++) {
            char ch = simpleName.charAt(i);
            if (Character.isUpperCase(ch))
                tableName = tableName + "_" + Character.toLowerCase(ch);
            else {
                tableName = tableName + ch;
            }
        }
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1);
        }
        return tableName;
    }

    public static String warpsavesql(Object entity, Map paramMap) {
        Class clazz = entity.getClass();
        List<String> fdNames = DbKit.getAllDBFields(clazz);
        String tableName = DbKit.getTableName(clazz);
        StringBuffer sql = new StringBuffer("INSERT INTO ").append(tableName).append("(");
        StringBuffer valueSql = new StringBuffer(" values(");
        for (int i = 0; i < fdNames.size(); i++) {
            String fdName = fdNames.get(i);// 字段名称
            String mapKey = ":" + conversionObjectAttribute(fdName);// 占位符
            paramMap.put(conversionObjectAttribute(fdName), DbKit.getPropertieValue(fdName, entity));
            if ((i + 1) == fdNames.size()) {
                sql.append(conversionObjectAttribute(fdName)).append(")");
                valueSql.append(mapKey).append(")");
                break;
            }
            sql.append(conversionObjectAttribute(fdName)).append(",");
            valueSql.append(mapKey).append(",");

        }
        sql.append(valueSql);// sql语句
        return sql.toString();
    }

    public static String warpupdatesql(Object entity, Map paramMap) {
        Class clazz = entity.getClass();
        List<String> fdNames = DbKit.getAllDBFields(clazz);
        String tableName = DbKit.getTableName(clazz);
        StringBuffer sql = new StringBuffer("UPDATE ").append(tableName).append("  SET  ");
        StringBuffer whereSQL = new StringBuffer(" WHERE id").append("=:").append("id");
        for (int i = 0; i < fdNames.size(); i++) {
            String fdName = fdNames.get(i);// 字段名称
            Object fdValue = fdName;
            if (fdName.equals("id")) {// 如果是ID
                if (fdValue != null) {// id有值
                    paramMap.put(fdName, DbKit.getPropertieValue(fdName, entity));
                }
                continue;
            }
          //  if (DbKit.getPropertieValue(fdName, entity) != null) { //2016-07-10 全部更新字段
                paramMap.put(conversionObjectAttribute(fdName), DbKit.getPropertieValue(fdName, entity));
                sql.append(conversionObjectAttribute(fdName)).append("=:").append(conversionObjectAttribute(fdName))
                        .append(",");
          //  }
        }
        String str = sql.toString();
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        return str + whereSQL;
    }

    public static Object getPropertieValue(String p, Object o) {
        Object _obj = null;
        for (Class<?> clazz = o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
                Method getMethod = pd.getReadMethod();// 获得get方法
                if (getMethod != null) {
                    _obj = getMethod.invoke(o);
                    break;
                }
            } catch (Exception e) {
                ExceptionUtil.throwException(e);
                return null;
            }

        }

        return _obj;
    }

    public static void setPropertieValue(String p, Object o, Object value) throws Exception {
        for (Class<?> clazz = o.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                PropertyDescriptor pd = new PropertyDescriptor(p, clazz);
                Method setMethod = pd.getWriteMethod();// 获得set方法
                if (setMethod != null) {
                    setMethod.invoke(o, value);
                    break;
                }
            } catch (Exception e) {
                ExceptionUtil.throwException(e);
            }
        }
    }

    public static String replaceFormatSqlOrderBy(String sql) {
        sql = sql.replaceAll("(\\s)+", " ");
        int index = sql.toLowerCase().lastIndexOf("order by");
        if (index > sql.toLowerCase().lastIndexOf(")")) {
            String sql1 = sql.substring(0, index);
            String sql2 = sql.substring(index);
            sql2 = sql2
                    .replaceAll(
                            "[oO][rR][dD][eE][rR] [bB][yY] [\u4e00-\u9fa5a-zA-Z0-9_.]+((\\s)+(([dD][eE][sS][cC])|([aA][sS][cC])))?(( )*,( )*[\u4e00-\u9fa5a-zA-Z0-9_.]+(( )+(([dD][eE][sS][cC])|([aA][sS][cC])))?)*",
                            "");
            return sql1 + sql2;
        }
        return sql;
    }

    public static String replaceFormatSqlFrom(String sql) {
        if (sql == null || sql.trim().length() == 0) {
            return sql;
        }
        //sql = sql.toLowerCase();
        int startIndex = sql.indexOf(" from ");
        int lastIndex = sql.lastIndexOf(" from ");
//        if (startIndex - lastIndex != 0) {
//            return sql;
//        }
        int groupIndex = sql.indexOf(" group ");
        if (groupIndex > 0) {
            sql = sql.substring(0, groupIndex);
        }
        int orderIndex = sql.indexOf(" order by ");
        if (orderIndex > 0) {
            sql = sql.substring(0, orderIndex);
        }
        return sql.substring(startIndex);
    }

    public static String conversionObjectAttribute(String name) {
        String returnName = "";
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (Character.isUpperCase(ch))
                returnName = returnName + "_" + Character.toLowerCase(ch);
            else {
                returnName = returnName + ch;
            }
        }
        if (returnName.startsWith("_")) {
            returnName = returnName.substring(1);
        }
        return returnName;
    }

    /**
     * 是否是java的基本类型
     * 
     * @param clazz
     * @return
     */
    public static boolean isBaseType(Class clazz) {
        if (clazz == null) {
            return false;
        }
        String className = clazz.getName().toLowerCase();
        if (className.startsWith("java.")) {
            return true;
        } else {
            return false;
        }
    }

    public static <T> String getTableName(Class entity) {
        String defaultTableName = getBaseTableNameByClass(entity);
        if (entity.getClass().isAnnotationPresent(TableRouterRule.class)) {
            TableRouterRule rule = (TableRouterRule) entity.getClass().getAnnotation(TableRouterRule.class);
            String key = rule.key();
            String type = rule.type();
            int count = rule.count();
            BaseRouterStrategy strategy = RouterManager.getRouters().get(type);
            if (strategy != null) {
                defaultTableName = strategy.getTableName(entity, key, count);
            }
            return defaultTableName;
        } else {
            // 不存在注解
            return defaultTableName;
        }

    }

    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0) ? true : false;
    }
    
    /**
     * 首字母变小写
     */
    public static String firstCharToLowerCase(String str) {
        Character firstChar = str.charAt(0);
        String tail = str.substring(1);
        str = Character.toLowerCase(firstChar) + tail;
        return str;
    }

    public static void main(String[] args) throws Exception {
         Map map=new HashMap<String ,Object>();
         map.put("id","1");
         map.put("name","gpp");
         map.put("age", "10");
       //  String s=warpsavesql(new com.jdk2010.framework.test.dal.Student(),map);
      //  System.out.println(s);
        System.out.println(conversionObjectAttribute("aa_bb"));
        
//        String sql="select t.*,a.swjgbm from skq_jqxx  t left join skq_nsrxx a on t.nsrwjbm=a.nsrwjbm left join security_organization b on a.swjgbm=b.swjgbm";
//        System.out.println(DbKit.replaceFormatSqlFrom(sql));
    }

}
