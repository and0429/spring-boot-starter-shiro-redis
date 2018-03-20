package com.shiwen.shiro.properties;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.realm.Realm;
import org.springframework.boot.context.properties.ConfigurationProperties;

import com.shiwen.shiro.exception.NotRealmException;

/**
 * 
 * @author and04
 *
 */
@ConfigurationProperties(prefix = Realms.PREFIX)
public class Realms {

	public static final String PREFIX = "shiro.realms";

	private List<String> realmClassNames = new ArrayList<>();

	public List<String> getRealmClassNames() {
		return realmClassNames;
	}

	public void setRealmClassNames(List<String> realmClassNames) {
		this.realmClassNames = realmClassNames;
	}

	public int size() {
		return realmClassNames.size();
	}

	public void empty() {
		realmClassNames.clear();
	}

	public boolean add(String e) {
		return realmClassNames.add(e);
	}

	public boolean remove(String e) {
		return realmClassNames.remove(e);
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
	public List<Realm> get() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException {
		List<Realm> realmsObj = new ArrayList<>();
		for (String realmClassName : realmClassNames) {
			Class<?> realm = Class.forName(realmClassName);
			Object instance = realm.newInstance();
			if (instance instanceof Realm) {
				realmsObj.getClass().getMethod("add", Object.class).invoke(realmsObj, instance);
			} else {
				throw new NotRealmException(realmClassName + " is not a realm instance");
			}
		}
		return realmsObj;
	}

}
