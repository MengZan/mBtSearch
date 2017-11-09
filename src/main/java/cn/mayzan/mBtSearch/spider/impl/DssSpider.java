package cn.mayzan.mBtSearch.spider.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cn.mayzan.mBtSearch.common.Constants;
import cn.mayzan.mBtSearch.entity.BtInfo;
import cn.mayzan.mBtSearch.spider.AbstractSpider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Spider;

public class DssSpider extends AbstractSpider {

	private final static String URL = "https://www.diaosisou.org";

	private final static int PAGE_SIZE = 10;

	private int pages = 10;

	protected boolean first = true;

	public DssSpider(String key, int total, List<BtInfo> resultList) {
		super(key.replace(" ", "%20"), total, resultList);
		this.pages = (int) Math.ceil(total * 1.0 / PAGE_SIZE);
	}

	@Override
	public void process(Page page) {
		// 初始化列表页
		if (first) {
			synchronized (this) {
				if (first) {
					this.first = false;
					logger.warn("{}:初始化列表", this.getClass().getName());
					List<String> targetRequests = new ArrayList<>(pages);
					for (int i = 1; i <= pages; i++) {
						targetRequests.add(URL + "/list/" + key + "/" + i);
					}
					page.addTargetRequests(targetRequests);
				}
			}
		}
		// 列表页
		if (page.getUrl().toString().contains("list")) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class='T1']/a/@href").all());
		}
		// 详情页
		else {
			String name = page.getHtml().xpath("//div[@class='T2']/text()").toString();
			BtInfo bt = new BtInfo();
			bt.setName(name);
			resultList.add(bt);
		}
	}

	@Override
	public void run() {
		Spider.create(this).addUrl(URL + "/list/" + key + "/" + 1).thread(Constants.SPIDER_THREADS).run();
	}

	public static void main(String[] args) {
		List<BtInfo> resultList = Collections.synchronizedList(new LinkedList<BtInfo>());
		AbstractSpider spider = new DssSpider("111", 10, resultList);
		spider.run();
		System.out.println(resultList.size());
	}
}
