package com.jdk2010.framework.dal.dialect;

import org.springframework.stereotype.Component;

import com.jdk2010.framework.util.Page;

@Component("mssqlDialect")
public class MssqlDialect implements IDialect {

    public String getPageSql(String sql, String orderby, Page page) {
        // 设置分页参数
        int pageSize = page.getPageSize();
        int pageNo = page.getPageIndex();
        // 去掉无用的空格
        sql = sql.trim();
        // 去掉select
        if (sql.toLowerCase().startsWith("select")) {
            sql = sql.substring(6);
        }

        StringBuffer sb = new StringBuffer();
        sb.append("SELECT TOP ");
        sb.append(pageSize);
        sb.append(" * from (SELECT ROW_NUMBER() OVER (");
        sb.append(orderby);
        sb.append(")  frame_page_sql_row_number,");
        sb.append(sql);
        sb.append("  ) frame_sql_temp_table WHERE frame_page_sql_row_number > ");
        sb.append(pageSize * (pageNo - 1));
        sb.append(" order by frame_page_sql_row_number ");
        return sb.toString();
    }

    public String getDataDaseType() {
        return "mssql";
    }

    public boolean isRowNumber() {
        return true;
    }

}
