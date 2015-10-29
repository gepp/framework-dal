package com.jdk2010.framework.dal.client.support.shard;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.dialect.IDialect;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;
import com.jdk2010.framework.util.StringUtils;

public abstract class DefaultShardingDalClient implements DalClient, InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private boolean showSql;

    private String defaultDataSource;

    public String getDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(String defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    Map<String, DataSource> dataSources;

    Map<String, NamedParameterJdbcTemplate> shards = new HashMap<String, NamedParameterJdbcTemplate>();

    public Map<String, DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(Map<String, DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public void setShowSql(String paraShowSql) {
        if (paraShowSql == null) {
            showSql = false;
        } else if (paraShowSql.equals("true")) {
            showSql = true;
        } else {
            showSql = false;
        }
    }

    public NamedParameterJdbcTemplate lookupDateSourceName(final Object parameterObject) {
        if (shards.get(shardingRule(parameterObject)) == null || "".equals(shards.get(shardingRule(parameterObject)))) {
            logger.debug("使用默认数据源-->" + defaultDataSource);
            return shards.get(defaultDataSource);

        } else {
            logger.debug("切换数据源-->" + shardingRule(parameterObject));
            return shards.get(shardingRule(parameterObject));
        }

    }

    public abstract String shardingRule(final Object parameterObject);

    IDialect dialect;

    public boolean showsql() {
        return showSql;
    }

    private void logInfoSql(DbKit dbKit) {
        if (showsql()) {
            logger.debug("sql:" + dbKit.getSql());
            logger.debug("params:" + dbKit.getParamsToString());
        }
    }

    public Map<String, Object> queryForObject(DbKit dbKit) {
        Map<String, Object> map = null;
        try {
            logInfoSql(dbKit);

            map = lookupDateSourceName(dbKit.getParams()).queryForMap(dbKit.getSql(), dbKit.getParams());
        } catch (EmptyResultDataAccessException e) {
            map = null;
            throw e;
        } finally {

        }
        return map;
    }

    public Map<String, Object> queryForObject(String sql) {
        return queryForObject(new DbKit(sql));
    }

    public <T> T queryForObject(DbKit dbKit, Class<T> clazz) {
        logInfoSql(dbKit);
        T t = null;
        try {
            t = (T) lookupDateSourceName(dbKit.getParams()).queryForObject(dbKit.getSql(), dbKit.getParams(),
                    BeanPropertyRowMapper.newInstance(clazz));
        } catch (EmptyResultDataAccessException e) {
            t = null;
            logger.error(e.getMessage());
            throw e;

        } finally {

        }
        return t;
    }

    public <T> T queryForObject(String sql, Class<T> clazz) {
        return queryForObject(new DbKit(sql), clazz);
    }

    public List<Map<String, Object>> queryForList(DbKit dbKit) {
        logInfoSql(dbKit);
        List<Map<String, Object>> list = null;
        try {
            list = lookupDateSourceName(dbKit.getParams()).queryForList(dbKit.getSql(), dbKit.getParams());
        } catch (Exception e) {
            logger.error(e.getMessage());
            ExceptionUtil.throwException(e);
        }
        return list;
    }

    public List<Map<String, Object>> queryForList(String sql) {
        return queryForList(new DbKit(sql));
    }

    public Integer update(DbKit dbKit) {
        logInfoSql(dbKit);
        Integer returnInteger = 0;
        try {
            returnInteger = lookupDateSourceName(dbKit.getParams()).update(dbKit.getSql(), dbKit.getParams());
        } catch (Exception e) {
            logger.error(e.getMessage());
            ExceptionUtil.throwException(e);
        }
        return returnInteger;
    }

    public Integer update(String sql) {
        return update(new DbKit(sql));
    }

    public Integer update(Model model) {
        Map paramMap = new HashMap();
        String sql = DbKit.warpupdatesql(model, paramMap);
        logInfoSql(new DbKit(sql));
        if (paramMap.size() > 1) {
            return lookupDateSourceName(model).update(sql, paramMap);
        } else {
            return 0;
        }
    }

    public Integer save(Model model) {
        Map paramMap = new HashMap();
        String sql = DbKit.warpsavesql(model, paramMap);
        return lookupDateSourceName(model).update(sql, paramMap);
    }

    public Integer deleteByID(Object id, Class clazz) {
        String tableName = DbKit.getTableName(clazz);
        String sql = "delete from " + tableName + " where id='" + id + "'";
        return update(new DbKit(sql));
    }

    public Integer deleteByIDS(Object ids, Class clazz) {
        Integer rowCount = 0;
        if (ids == null) {
            logger.info("ids is null");
            return 0;
        }
        if (ids.toString() == null) {
            return 0;
        }

        String[] idStrs = ids.toString().split(",");
        String tableName = DbKit.getTableName(clazz);

        for (String id : idStrs) {
            String sql = "delete from " + tableName + " where id='" + id + "'";
            rowCount = update(new DbKit(sql));
        }

        return rowCount;
    }

    public <T> T findById(Object id, Class clazz) {
        String tableName = DbKit.getTableName(clazz);
        String sql = "select * from " + tableName + " where id='" + id + "'";
        return (T) queryForObject(new DbKit(sql), clazz);
    }

    public <T> List<T> queryForList(DbKit dbKit, Class<T> clazz) {
        logInfoSql(dbKit);
        return lookupDateSourceName(dbKit.getParams()).query(dbKit.getSql(), dbKit.getParams(),
                BeanPropertyRowMapper.newInstance(clazz));
    }

    public <T> List<T> queryForList(String sql, Class<T> clazz) {
        return queryForList(new DbKit(sql), clazz);
    }

    public Page queryForPageList(DbKit dbKit, Page page) {
        Page pageResult = warpPageSql(page, dbKit);
        String pageSql = pageResult.getSql();
        if (pageSql == null)
            return null;
        logInfoSql(dbKit);
        page.setList(lookupDateSourceName(dbKit.getParams()).queryForList(pageSql, dbKit.getParams()));
        return page;
    }

    public Page queryForPageList(String sql, Page page) {
        return queryForPageList(new DbKit(sql), page);
    }

    public Page queryForPageList(String sql, Page page, Class clazz) {
        return queryForPageList(new DbKit(sql), page, clazz);
    }

    public Page queryForPageList(DbKit dbKit, Page page, Class clazz) {
        Page pageResult = warpPageSql(page, dbKit);
        String pageSql = pageResult.getSql();
        if (pageSql == null)
            return null;
        logInfoSql(dbKit);
        page.setList(lookupDateSourceName(dbKit.getParams()).query(pageSql, dbKit.getParams(),
                BeanPropertyRowMapper.newInstance(clazz)));
        return page;

    }

    private Page warpPageSql(Page page, DbKit dbKit) {
        String sql = dbKit.getSql();
        String orderSql = dbKit.getOrderSql();
        Map<String, Object> paramMap = dbKit.getParams();
        if (StringUtils.isBlank(sql)) {
            return null;
        }
        if (page != null && StringUtils.isNotBlank(page.getOrder())) {
            String _order = page.getOrder().trim();
            if (_order.indexOf(" ") > -1 || _order.indexOf(";") > -1) {
                orderSql = " order by id asc ";
            } else {
                String _sort = page.getSort();
                if (_sort == null) {
                    _sort = "";
                }
                _sort = _sort.trim().toLowerCase();
                if ((!"asc".equals(_sort)) && (!"desc".equals(_sort))) {
                    _sort = "";
                }
                orderSql = " order by " + page.getOrder() + " " + _sort;
            }
        }

        Integer count = null;
        String countSql = "select count(*) " + DbKit.replaceFormatSqlFrom(dbKit.getSql());
        logger.debug("countSql:" + countSql);
        count = lookupDateSourceName(paramMap).queryForObject(countSql, paramMap, Integer.class);
        if (count == 0) {
            page.setSql(null);
        } else {
            page.setTotalCount(count);
        }
        page.setSql(dialect.getPageSql(sql, orderSql, page));
        return page;
    }

    public <T> T queryColumn(String sql, String param) {
        List<Map<String, Object>> list = queryForList(sql);
        Map<String, Object> result = null;
        if (list.size() > 0) {
            result = list.get(0);
        }
        T temp = null;
        if (result != null) {
            try {
                temp = (T) result.get(param);
            } catch (Exception e) {
                logger.error(e.getMessage());
                temp = null;
            } finally {

            }
        }
        return temp;

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("初始化datasource...");
        for (String key : dataSources.keySet()) {
            shards.put(key, new NamedParameterJdbcTemplate(dataSources.get(key)));
        }
    }

}
