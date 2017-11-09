package cn.mayzan.mBtSearch.spider;

import java.util.HashSet;
import java.util.Set;

public class SpiderClasses {

	public final static Set<String> SPIDER_SET = new HashSet<>();
	
	static{
//		SPIDER_SET.add("cn.mayzan.mBtSearch.spider.impl.DssSpider");
		SPIDER_SET.add("cn.mayzan.mBtSearch.spider.impl.TestSpider");
		SPIDER_SET.add("cn.mayzan.mBtSearch.spider.impl.TestSpider2");
	}
}
