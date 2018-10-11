package com.shenhesoft.logistics.common.page;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 绑定JSON/自定义 数据到 Map.
 * <p>
 * 默认自定义的MethodArgumentResolver是放在预定义之后，而且如果我们使用Map接收时，会自动绑定到Model上。
 * </p>
 *
 * @param <K>
 *            key
 * @param <V>
 *            value
 * 
 * @author LiuJiefeng
 */
public class MapWapper<K, V> {

	private Map<K, V> innerMap = new HashMap<K, V>();

	public void setInnerMap(Map<K, V> innerMap) {
		this.innerMap = innerMap;
	}
	
	public Map<K, V> getInnerMap() {
		if(null==innerMap){
			return new HashMap<K, V>();
		}
		return innerMap;
	}

	public void clear() {
		innerMap.clear();
	}

	public boolean containsKey(Object key) {
		return innerMap.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return innerMap.containsValue(value);
	}

	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return innerMap.entrySet();
	}

	public boolean equals(Object obj) {
		return innerMap.equals(obj);
	}

	public V get(Object key) {
		return innerMap.get(key);
	}

	public int hashCode() {
		return innerMap.hashCode();
	}

	public boolean isEmpty() {
		return innerMap.isEmpty();
	}

	public Set<K> keySet() {
		return innerMap.keySet();
	}

	public V put(K key, V value) {
		return innerMap.put(key, value);
	}

	public void putAll(Map<? extends K, ? extends V> map) {
		innerMap.putAll(map);
	}

	public V remove(Object key) {
		return innerMap.remove(key);
	}

	public int size() {
		return innerMap.size();
	}

	public Collection<V> values() {
		return innerMap.values();
	}

	@Override
	public String toString() {
		return innerMap.toString();
	}

}
