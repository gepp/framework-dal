
package com.jdk2010.framework.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

 
@Component("springUtils")
public class SpringUtils  implements ApplicationContextAware {
	
	
	private static ApplicationContext applicationContext;

	public SpringUtils() {
		
	}

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.applicationContext=context;
	}
	/**
	 * 根据beanName 获取 spring bean
	 * @param beanName
	 * @return Object
	 */
	public static  Object getBean(String beanName){
		if(beanName==null)return null;
	    return 	applicationContext.getBean(beanName);
	}
	/**
	 * 根据bean type 获取springBean
	 * @param clazz
	 * @return
	 */
	public static Object getBeanByType(Class clazz){
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * 获取 Spring applicationContext
	 * @return
	 */
	public static ApplicationContext getContext() {
		return applicationContext;
	}
	

}
