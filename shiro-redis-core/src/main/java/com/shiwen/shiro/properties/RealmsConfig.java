package com.shiwen.shiro.properties;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.realm.Realm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.shiwen.shiro.exception.NoRealmInSpringBeanFactoryException;

/**
 * 
 * @author and04
 *
 */
@ConfigurationProperties(prefix = RealmsConfig.PREFIX)
public class RealmsConfig {

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
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 */
	public List<Realm> get(ApplicationContext context)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
		List<Realm> realmsObj = new ArrayList<>();
		for (String realmSpringBeanName : realmSpringBeanNames) {
			Realm realm = context.getBean(realmSpringBeanName, Realm.class);
			if (realm != null)
				realmsObj.add(realm);
			else
				throw new NoRealmInSpringBeanFactoryException("It has no Bean named " + realmSpringBeanName + " in spring bean factory!");
		}
		return realmsObj;
	}

}
