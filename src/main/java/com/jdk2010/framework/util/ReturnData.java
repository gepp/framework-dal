package com.jdk2010.framework.util;

import java.util.HashMap;
import java.util.Map;

public class ReturnData {
	private String status;
	private String message;
	private Map data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private ReturnData(){
		
	}
	
	public ReturnData(String status,String message){
		this.status=status;
		this.message=message;
		data=new HashMap<String,Object>();
	}
	
	public  void put(String key,Object obj){
		data.put(key, obj);
	}
	
	public Object get(String key){
		return  data.get(key);
	}
	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	
	
	
	
}
