package com.jdk2010.framework.dal.client.support.router.method;

import com.jdk2010.framework.dal.client.support.router.BaseRouterStrategy;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.util.DbKit;

public class DalHash implements BaseRouterStrategy {

    @Override
    public String getTableName(Object obj, String key, Integer count) {
        if (obj == null || DbKit.isBlank(key) || count == null) {
            ExceptionUtil.throwException(new RuntimeException("dalHash参数不能为空"));
        }
        String tableName = DbKit.getBaseTableNameByClass(obj.getClass());
        String keyValue =DbKit.getPropertieValue(key, obj).toString();
        int hashCode = keyValue.hashCode();
        return tableName + hashCode % count;
    }
}
