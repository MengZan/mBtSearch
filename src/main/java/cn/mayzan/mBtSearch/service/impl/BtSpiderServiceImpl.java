package cn.mayzan.mBtSearch.service.impl;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

import cn.mayzan.mBtSearch.common.CacheUtils;
import cn.mayzan.mBtSearch.common.Constants;
import cn.mayzan.mBtSearch.common.ThreadPoolUtils;
import cn.mayzan.mBtSearch.common.cache.AbstractCache;
import cn.mayzan.mBtSearch.entity.BtInfo;
import cn.mayzan.mBtSearch.service.BtSpiderService;
import cn.mayzan.mBtSearch.spider.AbstractSpider;
import cn.mayzan.mBtSearch.spider.SpiderClasses;

@Service
public class BtSpiderServiceImpl implements BtSpiderService {

	@Override
	public List<BtInfo> searchBt(final String key, final int page, final int pageSize) {
		AbstractCache<String, List<BtInfo>> cache = CacheUtils.getLRUCache();
		List<BtInfo> result = new ArrayList<>(pageSize);
		int start = pageSize * (page - 1) + 1;
		int end = pageSize * page;
		// 查询缓存开始
		if (cache.containsKey(key)) {
			List<BtInfo> cacheList = cache.get(key);
			if (cacheList.size() >= end) {
				for (int i = start - 1; i < end; i++) {
					result.add(cacheList.get(i));
				}
				return result;
			} else {
				return null;
			}
		}
		// 查询缓存结束
		final List<BtInfo> list = Collections.synchronizedList(new LinkedList<BtInfo>());
		ExecutorService pool = ThreadPoolUtils.getFixedThreadPool();
		final int averageTips = Constants.TIPS_NUMBER / SpiderClasses.SPIDER_SET.size();
		for (final String className : SpiderClasses.SPIDER_SET) {
			pool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						// 启动爬虫
						Class clazz = Class.forName(className);
						Constructor c = clazz.getConstructor(String.class, int.class, List.class);
						AbstractSpider spider = (AbstractSpider) c.newInstance(key, averageTips, list);
						spider.run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		int size = end <= 0 ? 10 : end;
		while (list.size() < size) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 插入缓存
		cache.put(key, list);
		for (int i = start - 1; i < end; i++) {
			result.add(list.get(i));
		}
		return result;
	}

	@Override
	public void sort(List<BtInfo> list, String sortBy) {
	}

	public static void main(String[] args) throws InterruptedException {
		BtSpiderServiceImpl s = new BtSpiderServiceImpl();
		List<BtInfo> resultList = s.searchBt("对", 1, 10);
		System.out.println("===============");
		for (BtInfo bt : resultList) {
			System.out.println(bt.getName());
		}
	}

}
