package com.jdk2010.framework.test.router.custom;

import com.jdk2010.framework.dal.client.support.router.BaseRouterStrategy;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.util.DbKit;

public class StudentRuleCustom implements BaseRouterStrategy {
    /**
     * 自定义规则
     * 如果key是5016 表名是南京
     * 如果是1300 表名是广州
     * 如果是2700 表名是长春
     */
    @Override
    public String getTableName(Object obj, String key, Integer count) {
        if (obj == null || DbKit.isBlank(key) || count == null) {
            ExceptionUtil.throwException(new RuntimeException("参数不完整"));
        }
        String tableName = DbKit.getBaseTableNameByClass(obj.getClass());
        String keyValue = DbKit.getPropertieValue(key, obj).toString();
        if (keyValue.equals("5016")) {
            tableName = tableName + "_nanjing";
        } else if (keyValue.equals("1300")) {
            tableName = tableName + "_guangzhou";
        } else if (keyValue.equals("2700")) {
            tableName = tableName + "_changchun";
        }
        return tableName;
    }
}
