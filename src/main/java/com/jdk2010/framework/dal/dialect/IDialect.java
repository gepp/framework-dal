/**
 * 数据库方言接口
 */

package com.jdk2010.framework.dal.dialect;

import com.jdk2010.framework.util.Page;

public interface IDialect {

    String getPageSql(String sql, String orderby, Page page);

    /**
     * 获取数据库类型
     * 
     * @return
     */
    String getDataDaseType();

    /**
     * 是否包含 rownum 列
     * 
     * @return
     */
    boolean isRowNumber();

}
