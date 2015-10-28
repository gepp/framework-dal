package com.jdk2010.framework.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.dal.parse.annotation.TableField;

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
        if (StringUtils.isBlank(allNames))
            return null;

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

    public static String getTableNameByClass(Class clazz) {
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
        String tableName = DbKit.getTableNameByClass(clazz);
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
        String tableName = DbKit.getTableNameByClass(clazz);
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
            if (DbKit.getPropertieValue(fdName, entity) != null) {
                paramMap.put(conversionObjectAttribute(fdName), DbKit.getPropertieValue(fdName, entity));
                sql.append(conversionObjectAttribute(fdName)).append("=:").append(conversionObjectAttribute(fdName))
                        .append(",");
            }
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
        if (StringUtils.isBlank(sql)) {
            return sql;
        }
        sql = sql.toLowerCase();
        int startIndex = sql.indexOf(" from ");
        int lastIndex = sql.lastIndexOf(" from ");
        if (startIndex - lastIndex != 0) {
            return sql;
        }
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

    public static void main(String[] args) throws Exception {
        // Map map=new HashMap<String ,Object>();
        // map.put("id","id");
        // map.put("username","kk");
        // String s=warpsavesql(new SecurityMenu(),map);
        // System.out.println(s);
        // s=warpupdatesql(new SecurityMenu(),map);
        // System.out.println(s);
        // User user=new User();
        // user.setId("123");
        // System.out.println("before_name:"+user.getName());
        // System.out.println("============");
        // setPropertieValue("name",user,"ykk");
        // System.out.println("after name:"+user.getName());
    }

}
