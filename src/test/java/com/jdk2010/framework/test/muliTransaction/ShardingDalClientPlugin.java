package com.jdk2010.framework.test.muliTransaction;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jdk2010.framework.dal.client.support.shard.DefaultShardingDalClient;

public class ShardingDalClientPlugin extends DefaultShardingDalClient {
    
    /**
     * ǰ��ϵͳĬ��id������int unsign(b-tree) �����uuid���ƣ���hash֮����mod
     * �Լ�д�������� mod  3
     * 6%3=0  return dataSource1   ��applicationContext_shard.xml �����õ� datasource����
     * 7%3=1  return dataSource2
     * 8%3=2  return dataSource3 
     * 
     * ���� �������и��ؼ���,���� 
     * ��˾����=1   return dataSource1
     * ��˾����=2   return dataSource2
     * ��˾����=3   return dataSource3
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
                map=BeanUtils.describe(parameterObject); // ʵ��ת��map
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
