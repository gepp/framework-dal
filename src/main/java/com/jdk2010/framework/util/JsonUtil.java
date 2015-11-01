package com.jdk2010.framework.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {

    public static String toJson(Object value) {
        return JSON.toJSONString(value);
    }

    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map jsonMap = JSON.parseObject(jsonStr, HashMap.class);
        return jsonMap;
    }

    public static <T> T toModel(String jsonStr, Class<T> clazz) {
        return JSON.parseObject(jsonStr, clazz);
    }

    public static void main(String[] args) {
        String jsonStr = "[{\"paraAlias\":\"\",\"sid\":\"P1\",\"paraName\":\"风格\"},{\"paraAlias\":\"\",\"sid\":\"P2\",\"paraName\":\"通勤\"}]";
        List list = toModel(jsonStr, List.class);
        for (int i = 0; i < list.size(); i++) {
            JSONObject obj = (JSONObject) list.get(i);
            System.out.println(obj.get("sid"));

        }
    }
}
