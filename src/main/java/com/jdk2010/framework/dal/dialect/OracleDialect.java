package com.jdk2010.framework.dal.dialect;

import org.springframework.stereotype.Component;

import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;

@Component("oracleDialect")
public class OracleDialect implements IDialect {

    public String getPageSql(String sql, String orderby, Page page) {
        // 设置分页参数
        int satrt = (page.getPageIndex() - 1) * page.getPageSize() + 1;
        int end = page.getPageIndex() * page.getPageSize();

        StringBuffer sb = new StringBuffer();
        sb.append("select * from ( select  rownum frame_page_sql_row_number ,frame_sql_temp_table1.* from (");
        sb.append(sql);
        if (DbKit.isBlank(orderby)) {
            sb.append(" ").append(orderby);
        }
        sb.append(") frame_sql_temp_table1 where rownum <= ").append(end).append(") frame_sql_temp_table2");
        sb.append(" where frame_sql_temp_table2.frame_page_sql_row_number >= ").append(satrt);

        return sb.toString();
    }

    public String getDataDaseType() {
        return "oracle";
    }

    public boolean isRowNumber() {
        return true;
    }

}
