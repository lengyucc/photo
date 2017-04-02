package com.antbean.photo.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ResultJson implements Map<String, Object> {

	private Map<String, Object> map = new HashMap<String, Object>();

	private ResultJson() {
	}

	public static ResultJson getSuccessResult() {
		ResultJson result = new ResultJson();
		result.put("success", true);
		return result;
	}

	public static ResultJson getFailResult(String errMsg) {
		ResultJson result = new ResultJson();
		result.put("success", false);
		result.put("errMsg", errMsg);
		return result;
	}

	public static ResultJson getFailResult(Exception e) {
		return getFailResult(e.getMessage());
	}

	public ResultJson cput(String key, Object value) {
		map.put(key, value);
		return this;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return map.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<Object> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

}
