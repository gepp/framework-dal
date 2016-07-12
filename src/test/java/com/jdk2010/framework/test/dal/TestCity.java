package com.jdk2010.framework.test.dal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.util.JsonUtil;

public class TestCity {
    public static void main(String[] args) throws IOException {
        BeanFactory factory = new ClassPathXmlApplicationContext("dal/applicationContext.xml");
        DalClient dalClient = factory.getBean(DalClient.class);
        InputStream in = new FileInputStream("c:\\test.js");
        String str = "";
        byte[] b = new byte[1024];
        int length = 0;
        while ((length = in.read(b)) != -1) {
            str = str + new String(b, 0, length, "UTF-8");
        }
        List list = JsonUtil.toModel(str, List.class);
        for(int i=0;i<list.size();i++){
            Map province=(Map)list.get(i);
            String provincename=province.get("text")+"";
            String provincecode=province.get("value")+"";
            dalClient.update("insert into base_province(code,name) values('"+provincecode+"','"+provincename+"')");
            
            List citylist = JsonUtil.toModel(province.get("children")+"", List.class);
            for(int j=0;j<citylist.size();j++){
                Map city=(Map)citylist.get(j);
                String cityname=city.get("text")+"";
                String citycode=city.get("value")+"";
                dalClient.update("insert into base_city(code,name,provincecode) values('"+citycode+"','"+cityname+"','"+provincecode+"')");
                List countylist = JsonUtil.toModel(city.get("children")+"", List.class);
                if(countylist!=null){
                for(int k=0;k<countylist.size();k++){
                    Map county=(Map)countylist.get(k);
                    String countyname=county.get("text")+"";
                    String countycode=county.get("value")+"";
                    dalClient.update("insert into base_county(code,name,citycode) values('"+countycode+"','"+countyname+"','"+citycode+"')");
                }
                }
            
            }
            
        }
        
        

    }
}
