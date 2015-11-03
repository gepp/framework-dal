package com.jdk2010.framework.test.router.custom;

import com.jdk2010.framework.dal.client.support.router.BaseRouterStrategy;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.StringUtils;

public class StudentRuleCustom implements BaseRouterStrategy {
    /**
     * �Զ������
     * ���key��5016 �������Ͼ�
     * �����1300 �����ǹ���
     * �����2700 �����ǳ���
     */
    @Override
    public String getTableName(Object obj, String key, Integer count) {
        if (obj == null || StringUtils.isBlank(key) || count == null) {
            ExceptionUtil.throwException(new RuntimeException("����������"));
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
