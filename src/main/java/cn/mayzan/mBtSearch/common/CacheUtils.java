package cn.mayzan.mBtSearch.common;

import java.util.List;

import cn.mayzan.mBtSearch.common.cache.AbstractCache;
import cn.mayzan.mBtSearch.common.cache.LRUCache;
import cn.mayzan.mBtSearch.entity.BtInfo;

public class CacheUtils {

	private static AbstractCache<String, List<BtInfo>> cache;

	private CacheUtils() {
	}

	public static AbstractCache<String, List<BtInfo>> getLRUCache() {
		if (cache == null) {
			synchronized (CacheUtils.class) {
				if (cache == null) {
					cache = new LRUCache<>(Constants.CACHE_CAPACITY);
				}
			}
		}
		return cache;
	}

}
