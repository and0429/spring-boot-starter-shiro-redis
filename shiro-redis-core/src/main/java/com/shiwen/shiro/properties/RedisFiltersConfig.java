package com.shiwen.shiro.properties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.shiwen.shiro.exception.NotFilterException;

/**
 * 
 * @author zhangkai
 *
 */
@ConfigurationProperties(prefix = RedisFiltersConfig.prefix)
public class RedisFiltersConfig {

	public static final String prefix = "shiro.filters";

	private Map<String, String> filterClassNames = new LinkedHashMap<>();

	public Map<String, String> getFilterClassNames() {
		return filterClassNames;
	}

	public void setFilterClassNames(Map<String, String> filterClassNames) {
		this.filterClassNames = filterClassNames;
	}

	public int size() {
		return this.filterClassNames.size();
	}

	public String put(String key, String filterClassName) {
		return this.filterClassNames.put(key, filterClassName);
	}

	public void putAdd(Map<String, String> map) {
		this.filterClassNames.putAll(map);
	}

	public String remove(String key) {
		return this.filterClassNames.remove(key);
	}

	public void clear() {
		this.filterClassNames.clear();
	}

	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public Map<String, Filter> get() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Map<String, Filter> filters = new LinkedHashMap<>();
		for (Map.Entry<String, String> entry : filterClassNames.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			Class<?> clazz = Class.forName(value);
			Object instance = clazz.newInstance();
			if (instance instanceof Filter) {
				Method method = Map.class.getMethod("put", Object.class, Object.class);
				method.invoke(filters, key, instance);
			} else {
				throw new NotFilterException(value + " is not a Filter instance");
			}
		}
		return filters;
	}

}
