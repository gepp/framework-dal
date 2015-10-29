package com.jdk2010.framework.dal.client.support.router;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RouterManager {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static  Map<String, BaseRouterStrategy> routers = new HashMap<String, BaseRouterStrategy>();

    public static  Map<String, BaseRouterStrategy> getRouters() {
        return routers;
    }

    public void setRouters(Map<String, BaseRouterStrategy> routers) {
        this.routers = routers;
    }
    
   

}
