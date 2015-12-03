package com.jdk2010.framework.service;

import java.util.List;
import java.util.Map;

import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;

public interface IAbstractBaseService {
    public Map<String, Object> queryForObject(DbKit dbKit);

    public <T> T queryForObject(DbKit dbKit, Class<T> clazz);

    public Integer update(DbKit dbKit);

    public Integer update(Model model);

    public Integer save(Model model);

    public Integer save(String sql, Map<String, Object> params);

    public Integer save(DbKit dbKit);

    public Integer deleteByID(Object id, Class clazz);

    public Integer deleteByIDS(Object ids, Class clazz);

    public <T> T findById(Object id, Class clazz);

    public <T> List<T> queryForList(DbKit dbKit, Class<T> clazz);

    public List<Map<String, Object>> queryForList(DbKit dbKit);

    public Page queryForPageList(DbKit dbKit, Page page, Class clazz);

    public Page queryForPageList(DbKit dbKit, Page page);

    public Map<String, Object> queryForObject(String sql);

    public Integer update(String sql);

    public Page queryForPageList(String sql, Page page);

    public <T> T queryForObject(String sql, Class<T> clazz);

    public <T> List<T> queryForList(String sql, Class<T> clazz);

    public List<Map<String, Object>> queryForList(String sql);

    public <T> T queryColumn(String sql, String param);

    public Page queryForPageList(String sql, Page page, Class clazz);
}
