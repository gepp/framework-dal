package com.jdk2010.framework.dal.dialect;

import org.springframework.stereotype.Component;

import com.jdk2010.framework.util.Page;
import com.jdk2010.framework.util.StringUtils;

@Component("mysqlDialect")
public class MysqlDialect implements IDialect {

	public String getPageSql(String sql, String orderby, Page page) {
		// 设置分页参数
		int pageSize = page.getPageSize();
		int pageNo = page.getPageIndex();
		StringBuffer sb = new StringBuffer(sql);
		if (StringUtils.isNotBlank(orderby)) {
			sb.append(" ").append(orderby);
		}
		sb.append(" limit ").append(pageSize * (pageNo - 1)).append(",")
				.append(pageSize);
		
		return sb.toString();
	}

	public String getDataDaseType() {
		return "mysql";
	}

	public boolean isRowNumber() {
		return false;
	}

}
