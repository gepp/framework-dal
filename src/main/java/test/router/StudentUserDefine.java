package test.router;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.dal.parse.annotation.TableField;
import com.jdk2010.framework.dal.parse.annotation.TableRouterRule;

@TableRouterRule(type = "userDefine", key = "name", count = 10)
public class StudentUserDefine extends Model<Model> {
    private Long id;
    private String name;
    private String age;

    @TableField
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
