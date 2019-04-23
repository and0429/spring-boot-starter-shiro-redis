package com.shiwen.shiro.properties;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author and04
 *
 */
@ConfigurationProperties(prefix = RealmsConfig.PREFIX)
public class RealmsConfig {

	public static final Logger logger = LoggerFactory.getLogger(RealmsConfig.class);

	public static final String PREFIX = "shiro.realms";

	/**
	 * 在Spring beanFactory中的类的名字，必须是Ream的实例
	 */
	private List<String> realmSpringBeanNames = new ArrayList<>();

	public List<String> getRealmSpringBeanNames() {
		return realmSpringBeanNames;
	}

	public void setRealmSpringBeanNames(List<String> realmSpringBeanNames) {
		this.realmSpringBeanNames = realmSpringBeanNames;
	}

	public int size() {
		return realmSpringBeanNames.size();
	}

	public void empty() {
		realmSpringBeanNames.clear();
	}

	public boolean add(String e) {
		return realmSpringBeanNames.add(e);
	}

	public boolean remove(String e) {
		return realmSpringBeanNames.remove(e);
	}

	/**
	 * 
	 * @return
	 */
	public List<Realm> get(ApplicationContext context) {
		List<Realm> realmsObj = new ArrayList<>();
		for (String realmSpringBeanName : realmSpringBeanNames) {
			try {
				Realm realm = context.getBean(realmSpringBeanName, Realm.class);
				realmsObj.add(realm);
			} catch (BeansException e) {
				logger.warn("It has no Bean named " + realmSpringBeanName + " in spring bean factory!", e);
			}
		}
		return realmsObj;
	}

}
