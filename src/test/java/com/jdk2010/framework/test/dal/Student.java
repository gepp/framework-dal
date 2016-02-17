package com.jdk2010.framework.test.dal;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.dal.parse.annotation.TableField;
import com.jdk2010.framework.dal.parse.annotation.TableRouterRule;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.test.dal.Student;

@Data
public class Student extends Model<Model> {
    
    private @TableField Integer id;
    private @TableField String name;
    private @TableField String age;
    
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
