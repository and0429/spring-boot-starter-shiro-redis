package com.shiwen.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.realm.Realm;

/**
 * 
 * @author and04
 *
 */
public class Realms {

	private List<Realm> realms = new ArrayList<>();

	private int size = 0;

	public int size() {
		return size;
	}

	public void empty() {
		realms = new ArrayList<>();
		size = 0;
	}

	public void add(Realm e) {
		boolean added = realms.add(e);
		if (added) {
			size++;
		}
	}

	public void remove(Realm e) {
		boolean removed = realms.remove(e);
		if (removed)
			size--;
	}

	public List<Realm> get() {
		return realms;
	}

}
