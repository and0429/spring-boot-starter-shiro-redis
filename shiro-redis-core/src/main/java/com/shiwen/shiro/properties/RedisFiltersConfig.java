package com.shiwen.shiro.properties;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

import com.shiwen.shiro.exception.NoFilterInSpringBeanFactoryException;

/**
 * 
 * @author zhangkai
 *
 */
@ConfigurationProperties(prefix = RedisFiltersConfig.prefix)
public class RedisFiltersConfig {

	public static final String prefix = "shiro.filters";

	private List<String> filterSpringBeanNames = new ArrayList<>();

	public List<String> getFilterSpringBeanNames() {
		return filterSpringBeanNames;
	}

	public void setFilterSpringBeanNames(List<String> filterSpringBeanNames) {
		this.filterSpringBeanNames = filterSpringBeanNames;
	}

	public int size() {
		return filterSpringBeanNames.size();
	}

	public boolean add(String key) {
		return filterSpringBeanNames.add(key);
	}

	public void AddAll(Collection<String> names) {
		filterSpringBeanNames.addAll(names);
	}

	public boolean remove(String key) {
		return filterSpringBeanNames.remove(key);
	}

	public void clear() {
		filterSpringBeanNames.clear();
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
	public Map<String, Filter> get(ApplicationContext context) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
		Map<String, Filter> filters = new LinkedHashMap<>();
		for (String filterSpringBeanName : filterSpringBeanNames) {
			Filter instance = context.getBean(filterSpringBeanName, Filter.class);
			if (instance != null)
				filters.put(filterSpringBeanName, instance);
			else
				throw new NoFilterInSpringBeanFactoryException(
						"It has no a Filter instance in spring bean factory named " + filterSpringBeanName);
		}
		return filters;
	}

}
