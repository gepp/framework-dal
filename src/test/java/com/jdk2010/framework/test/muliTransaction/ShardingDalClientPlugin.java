package com.jdk2010.framework.test.muliTransaction;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdk2010.framework.dal.client.support.shard.DefaultShardingDalClient;

public class ShardingDalClientPlugin extends DefaultShardingDalClient {
    
    /**
     * 前提系统默认id是自增int unsign(b-tree) 如果是uuid类似，则hash之后再mod
     * 自己写规则，例如 mod  3
     * 6%3=0  return dataSource1   是applicationContext_shard.xml 中配置的 datasource名字
     * 7%3=1  return dataSource2
     * 8%3=2  return dataSource3 
     * 
     * 例如 参数中有个关键字,例如 
     * 公司名称=1   return dataSource1
     * 公司名称=2   return dataSource2
     * 公司名称=3   return dataSource3
     */
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @SuppressWarnings("unchecked")
    @Override
    public String shardingRule(Object parameterObject) {
        String returnDataSourceName = "";
        try{
        if (parameterObject == null || "".equals(parameterObject)) {
            returnDataSourceName = "";
        } else {
            Map map = null;
            if (parameterObject instanceof Map || parameterObject instanceof HashMap) {
                map = (HashMap) parameterObject;
            } else {
                map=BeanUtils.describe(parameterObject); // 实体转成map
            }
            Integer id = Integer.parseInt((map.get("id").toString()));

            if (id % 3 == 0) {
                returnDataSourceName = "dataSourceMysql1";
            } else if (id % 3 == 1) {
                returnDataSourceName = "dataSourceMysql2";
            } else if (id % 3 == 2) {
                returnDataSourceName = "dataSourceMysql3";
            } else {
                returnDataSourceName = "";
            }
        }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            returnDataSourceName="";
        }finally{
            
        }
        //logger.info("returnDataSourceName:"+returnDataSourceName);
        return returnDataSourceName;
    }

}
