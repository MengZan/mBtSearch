package cn.mayzan.mBtSearch.spider.impl;

import java.util.List;

import cn.mayzan.mBtSearch.entity.BtInfo;
import cn.mayzan.mBtSearch.spider.AbstractSpider;
import us.codecraft.webmagic.Page;

public class TestSpider extends AbstractSpider {

	public TestSpider(String key, int pages, List<BtInfo> resultList) {
		super(key, pages, resultList);
	}

	@Override
	public void run() {
		for (int i = 0; i < total; i++) {
			System.out.println("Test:" + i);
			BtInfo bt = new BtInfo();
			bt.setName("TestSpider:" + i);
			resultList.add(bt);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void process(Page page) {
	}
}
