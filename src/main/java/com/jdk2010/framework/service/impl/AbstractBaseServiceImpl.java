package com.jdk2010.framework.service.impl;
import java.util.List;
import java.util.Map;
import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.dialect.IDialect;
import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.service.IAbstractBaseService;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;

public abstract class AbstractBaseServiceImpl implements IAbstractBaseService{

    public abstract DalClient getDalClient();

    public abstract IDialect getDialect();

    public Map<String, Object> queryForObject(DbKit dbKit) {
        return getDalClient().queryForObject(dbKit);
    }

    public <T> T queryForObject(DbKit dbKit, Class<T> clazz) {
        T t = getDalClient().queryForObject(dbKit, clazz);
        return t;
    }

    public Integer update(DbKit dbKit) {
        return getDalClient().update(dbKit);
    }

    public Integer update(Model model) {
        return getDalClient().update(model);
    }

    public Integer save(Model model) {
        return getDalClient().save(model);
    }

    public Integer save(String sql, Map<String, Object> params) {
        return getDalClient().save(sql, params);
    }

    public Integer save(DbKit dbKit) {
        return getDalClient().save(dbKit);
    }

    public Integer deleteByID(Object id, Class clazz) {
        return getDalClient().deleteByID(id, clazz);
    }

    public Integer deleteByIDS(Object ids, Class clazz) {
        return getDalClient().deleteByIDS(ids, clazz);
    }

    public <T> T findById(Object id, Class clazz) {
        return getDalClient().findById(id, clazz);
    }

    public <T> List<T> queryForList(DbKit dbKit, Class<T> clazz) {
        return getDalClient().queryForObjectList(dbKit, clazz);
    }

    public List<Map<String, Object>> queryForList(DbKit dbKit) {
        return getDalClient().queryForObjectList(dbKit);
    }

    public Page queryForPageList(DbKit dbKit, Page page, Class clazz) {
        return getDalClient().queryForPageList(dbKit, page, clazz);
    }

    public Page queryForPageList(DbKit dbKit, Page page) {
        return getDalClient().queryForPageList(dbKit, page);
    }

    public Map<String, Object> queryForObject(String sql) {
        return getDalClient().queryForObject(sql);
    }

    public Integer update(String sql) {
        return getDalClient().update(sql);
    }

    public Page queryForPageList(String sql, Page page) {
        return getDalClient().queryForPageList(sql, page);
    }

    public <T> T queryForObject(String sql, Class<T> clazz) {
        return getDalClient().queryForObject(sql, clazz);
    }

    public <T> List<T> queryForList(String sql, Class<T> clazz) {
        return getDalClient().queryForObjectList(sql, clazz);
    }

    public List<Map<String, Object>> queryForList(String sql) {
        return getDalClient().queryForObjectList(sql);
    }

    public <T> T queryColumn(String sql, String param) {
        return getDalClient().queryColumn(sql, param);
    }

    public Page queryForPageList(String sql, Page page, Class clazz) {
        return getDalClient().queryForPageList(sql, page, clazz);
    }
}
