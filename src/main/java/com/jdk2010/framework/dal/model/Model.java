package com.jdk2010.framework.dal.model;

import java.io.Serializable;

import com.jdk2010.framework.util.JsonUtil;

public abstract class Model<M extends Model<?>> implements Serializable {

    private static final long serialVersionUID = 1L;

    public String toJson() {
        return JsonUtil.toJson(this);
    }
    

}
