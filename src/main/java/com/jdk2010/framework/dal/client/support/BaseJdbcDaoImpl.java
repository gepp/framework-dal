package com.jdk2010.framework.dal.client.support;

import java.util.List;
import java.util.Map;

import com.jdk2010.framework.dal.client.DalClient;
import com.jdk2010.framework.dal.model.Model;
import com.jdk2010.framework.util.DbKit;
import com.jdk2010.framework.util.Page;

public  class BaseJdbcDaoImpl implements IBaseJdbcDao{
	
	private DalClient dalClient;
	
	public DalClient getDalClient() {
		return dalClient;
	}

	public void setDalClient(DalClient dalClient) {
		this.dalClient = dalClient;
	}
 	
	@Override
	public Map<String, Object> queryForObject(DbKit dbKit)  {
		return dalClient.queryForObject(dbKit);
	}
	
	@Override
	public Map<String, Object> queryForObject(String sql) {
		return queryForObject(new DbKit(sql));
	}

	
	@Override
	public <T> T queryForObject(DbKit dbKit, Class<T> clazz) {
		 return dalClient.queryForObject(dbKit,clazz);
	}
	
	@Override
	public <T> T queryForObject(String sql, Class<T> clazz) {
		return queryForObject(new DbKit(sql),clazz);
	}

	
	@Override
 	public List<Map<String, Object>> queryForList(DbKit dbKit)  {
 		return dalClient.queryForList(dbKit);
	}
	
	@Override
	public List<Map<String, Object>> queryForList(String sql){
		return queryForList(new DbKit(sql));
	}
	
	@Override
	public Integer update(DbKit dbKit) {
		return dalClient.update(dbKit);
	}
	
	@Override
	public Integer update(String sql) {
		return update(new DbKit(sql));
	}

	
	@Override
	public Integer update(Model model) {
		 return dalClient.update(model);
	}
	
	@Override
	public Integer save(Model model) {
			return dalClient.save(model);
	}
	
	@Override
	public Integer deleteByID(Object id, Class clazz) {
		return dalClient.deleteByID(id, clazz);
	}
	public Integer deleteByIDS(Object ids, Class clazz) {
		 
		return dalClient.deleteByIDS(ids, clazz);
	}
	
	@Override
	public <T> T findById(Object id, Class clazz) {
		return dalClient.findById(id, clazz);
	}
	
	@Override
	public <T> List<T> queryForList(DbKit dbKit,Class<T> clazz) {
		return dalClient.queryForList(dbKit,clazz);
	}
	
	@Override
	public <T> List<T> queryForList(String sql,Class<T> clazz) {
		return queryForList(new DbKit(sql),clazz);
	}

	
	@Override
	public Page queryForPageList(DbKit dbKit,Page page){
		return dalClient.queryForPageList(dbKit,page);
	}
	
	@Override
	public  Page queryForPageList(String sql,Page page){
		return queryForPageList(new DbKit(sql),page);
	}
	
	@Override
	public  Page queryForPageList(String sql,Page page,Class clazz) {
		return queryForPageList(new DbKit(sql),page,clazz);
	}
	
	@Override
	public Page queryForPageList(DbKit dbKit,Page page,Class clazz) {
		return dalClient.queryForPageList(dbKit, page,clazz);
	}
	@Override
 	public <T> T queryColumn(String sql,String param)  {
		 return dalClient.queryColumn(sql, param);
	}
 
	 
	
}
