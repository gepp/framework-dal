package test.router;

import com.jdk2010.framework.dal.client.support.router.BaseRouterStrategy;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.StringUtils;

public class DalUserDefine implements BaseRouterStrategy {

    @Override
    public String getTableName(Object obj, String key, Integer count) {
        if (obj == null || StringUtils.isBlank(key) || count == null) {
            ExceptionUtil.throwException(new RuntimeException("dalUserDefine参数不能为空"));
        }
        String tableName = DbKit.getBaseTableNameByClass(obj.getClass());
        String keyValue = DbKit.getPropertieValue(key, obj).toString();

        if (keyValue.equals("5016")) {
            tableName = tableName + "_nanjing";
        } else if (keyValue.equals("1300")) {
            tableName = tableName + "_guangzhou";
        } else if (keyValue.equals("2700")) {
            tableName = tableName + "_changchun";
        }
        return tableName;
    }
}
