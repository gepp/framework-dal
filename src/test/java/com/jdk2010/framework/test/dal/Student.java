package com.jdk2010.framework.test.dal;

import java.util.HashMap;
import java.util.Map;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.dal.parse.annotation.TableField;
import com.jdk2010.framework.util.DbKit;

public class Student extends Model<Model> {
    
    private  Integer id;
    private  String name;
    private  String age;
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

	public static void main(String[] args) throws Exception {

        Map map=new HashMap<String ,Object>();
        map.put("id","1");
        map.put("name","gpp");
        map.put("age", "10");
        Student stu=new Student();
        String s=DbKit.warpsavesql(stu,map);
       System.out.println(s);
       stu.setAge("10");
       System.out.println(DbKit.warpupdatesql(stu,map));
       
   }
}
