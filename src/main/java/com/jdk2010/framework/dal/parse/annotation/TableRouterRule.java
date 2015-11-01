package com.jdk2010.framework.dal.parse.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface TableRouterRule {

    String type(); // 分表类型, hash mod 自定义

    String key(); // 分表关键字

    int count(); // 如果hash,mod 需要判断分几张表,用到该参数

}
