package com.jdk2010.framework.test.router.mod;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.dal.parse.annotation.TableField;
import com.jdk2010.framework.dal.parse.annotation.TableRouterRule;

@TableRouterRule(type = "mod", key = "id", count = 3)
public class Student extends Model<Model> {
    private Integer id;
    private String name;
    private String age;

    @TableField
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @TableField
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @TableField
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
