package com.shiwen.shiro.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;

/**
 * 
 * @author zhangkai
 *
 */
@ConfigurationProperties(prefix = RedisFiltersConfig.prefix)
public class RedisFiltersConfig {

	public static final Logger logger = LoggerFactory.getLogger(RedisFiltersConfig.class);

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
	 */
	public Map<String, Filter> get(ApplicationContext context) {
		Map<String, Filter> filters = new LinkedHashMap<>();
		for (String filterSpringBeanName : filterSpringBeanNames) {
			try {
				Filter instance = context.getBean(filterSpringBeanName, Filter.class);
				filters.put(filterSpringBeanName, instance);
			} catch (BeansException e) {
				logger.warn("It has no a Filter instance in spring bean factory named " + filterSpringBeanName, e);
			}
		}
		return filters;
	}

}
