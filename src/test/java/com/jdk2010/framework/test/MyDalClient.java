package com.jdk2010.framework.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdk2010.framework.dal.client.support.DefaultShardingDalClient;

public class MyDalClient extends DefaultShardingDalClient {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public String shardingRule(Object parameterObject) {

        Map<String, Object> map = null;

        if (parameterObject == null || "".equals(parameterObject)) {
            return "";
        } else {
            if (parameterObject instanceof Map || parameterObject instanceof HashMap) {
                map=(HashMap<String, Object>) parameterObject;
            }else{
                try {
                    BeanUtils.populate(parameterObject, map);
                } catch (Exception e) {
                    logger.error("转换异常:"+e.getMessage());
                }  
            }
            if("1".equalsIgnoreCase((String) map.get("id"))){
                return "dataSourceMysql1";
            }else if("2".equalsIgnoreCase((String) map.get("id"))){
                return "dataSourceMysql2";
            }else {
                return "dataSourceMysql3";
            }

        }
        
        
    }

}
