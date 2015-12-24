package com.jdk2010.framework.dal.client.support;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.client.support.router.RouterManager;
import com.jdk2010.framework.dal.client.support.router.method.DalHash;
import com.jdk2010.framework.dal.client.support.router.method.DalMod;
import com.jdk2010.framework.dal.dialect.IDialect;
import com.jdk2010.framework.dal.dialect.MssqlDialect;
import com.jdk2010.framework.dal.dialect.MysqlDialect;
import com.jdk2010.framework.dal.dialect.OracleDialect;
import com.jdk2010.framework.dal.exception.ExceptionUtil;
import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;

public class DefaultDalClient implements DalClient, InitializingBean {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private DataSource dataSource;

    private boolean showSql;

    public void setShowSql(String paraShowSql) {
        if (paraShowSql == null) {
            showSql = false;
        } else if (paraShowSql.equals("true")) {
            showSql = true;
        } else {
            showSql = false;
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private IDialect dialect;

    private NamedParameterJdbcTemplate jdbcTemplate;

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
            map = jdbcTemplate.queryForMap(dbKit.getSql(), dbKit.getParams());
        } catch (EmptyResultDataAccessException e) {
            map = null;
            
        } finally {

        }
        return map;
    }

    public Map<String, Object> queryForObject(String sql) {
        return queryForObject(new DbKit(sql));
    }

    public <T> T queryForObject(DbKit dbKit, Class<T> clazz) {
        logInfoSql(dbKit);
        List<T> list = null;
        T t = null;
        try {
            list = jdbcTemplate.query(dbKit.getSql(), dbKit.getParams(), BeanPropertyRowMapper.newInstance(clazz));
            if (list.size() > 0) {
                t = list.get(0);
            }
        } catch (EmptyResultDataAccessException e) {
            t = null;
            logger.error(e.getMessage());
        } finally {

        }
        return t;
    }

    public <T> T queryForObject(String sql, Class<T> clazz) {
        return queryForObject(new DbKit(sql), clazz);
    }

    public List<Map<String, Object>> queryForObjectList(DbKit dbKit) {
        logInfoSql(dbKit);
        List<Map<String, Object>> list = null;
        try {
            list = jdbcTemplate.queryForList(dbKit.getSql(), dbKit.getParams());
        } catch (Exception e) {
            logger.error(e.getMessage());
            ExceptionUtil.throwException(e);
        }
        return list;
    }

    public List<Map<String, Object>> queryForObjectList(String sql) {
        return queryForObjectList(new DbKit(sql));
    }

    public Integer update(DbKit dbKit) {
        logInfoSql(dbKit);
        Integer returnInteger = 0;
        try {
            returnInteger = jdbcTemplate.update(dbKit.getSql(), dbKit.getParams());
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
            return jdbcTemplate.update(sql, paramMap);
        } else {
            return 0;
        }
    }

    public Integer save(Model model) {
        Map paramMap = new HashMap();
        String sql = DbKit.warpsavesql(model, paramMap);
        logger.info(sql);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer save(String sql, Map<String, Object> paramMap) {
        logger.info(sql);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Integer save(DbKit dbKit) {
        Map paramMap = dbKit.getParams();
        logger.info(dbKit.getSql());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(dbKit.getSql(), new MapSqlParameterSource(paramMap), keyHolder);
        return keyHolder.getKey().intValue();
    }

    public Integer deleteByID(Object id, Class clazz) {
        String tableName = DbKit.getTableName(clazz);
        String sql = "delete from " + tableName + " where id='" + id + "'";
        logger.info(sql);
        return update(new DbKit(sql));
    }

    public Integer deleteByIDS(Object ids, Class clazz) {
        Integer rowCount = 0;
        if (ids == null) {
            System.out.println("ids 为null");
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

    public <T> List<T> queryForObjectList(DbKit dbKit, Class<T> clazz) {
        logInfoSql(dbKit);
        return jdbcTemplate.query(dbKit.getSql(), dbKit.getParams(), BeanPropertyRowMapper.newInstance(clazz));
    }

    public <T> List<T> queryForObjectList(String sql, Class<T> clazz) {
        return queryForObjectList(new DbKit(sql), clazz);
    }

    public Page queryForPageList(DbKit dbKit, Page page) {
        Page pageResult = warpPageSql(page, dbKit);
        String pageSql = pageResult.getSql();
        if (pageSql == null)
            return null;
        logInfoSql(dbKit);
        page.setList(jdbcTemplate.queryForList(pageSql, dbKit.getParams()));
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

        page.setList(jdbcTemplate.query(pageSql, dbKit.getParams(), BeanPropertyRowMapper.newInstance(clazz)));
        return page;

    }

    private Page warpPageSql(Page page, DbKit dbKit) {
        String sql = dbKit.getSql();
        String orderSql = dbKit.getOrderSql();
        Map<String, Object> paramMap = dbKit.getParams();
        if (DbKit.isBlank(sql)) {
            return null;
        }
        if (page != null && !DbKit.isBlank(page.getOrder())) {// 如果page中包含
                                                              // 排序属性
            String _order = page.getOrder().trim();
            if (_order.indexOf(" ") > -1 || _order.indexOf(";") > -1) {// 认为是异常的,主要是防止注入
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
        count = jdbcTemplate.queryForObject(countSql, paramMap, Integer.class);
        if (count == 0) {
            page.setSql(null);
        } else {
            page.setTotalCount(count);
        }
        page.setSql(dialect.getPageSql(sql, orderSql, page));
        return page;
    }

    public <T> T queryColumn(String sql, String param) {
        List<Map<String, Object>> list = queryForObjectList(sql);
        Map<String, Object> result = null;
        if (list.size() > 0) {
            result = list.get(0);
        }
        T temp = null;
        if (result != null) {
            try {
                Object obj = result.get(param);
                if (obj != null) {
                    temp = (T) obj;
                }
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
        this.dataSource = dataSource;
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        RouterManager.getRouters().put("hash", new DalHash());
        RouterManager.getRouters().put("mod", new DalMod());
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            DatabaseMetaData metaData = connection.getMetaData();
            String productName = metaData.getDatabaseProductName().toLowerCase();
            if (productName.equals("mysql")) {
                dialect = new MysqlDialect();
            } else if (productName.equals("mssql")) {
                dialect = new MssqlDialect();
            } else if (productName.equals("oracle")) {
                dialect = new OracleDialect();
            }

        } catch (SQLException e) {
            logger.error(e.getMessage());
            ExceptionUtil.throwException(e);
        } finally {
            connection.close();
        }

    }

}
