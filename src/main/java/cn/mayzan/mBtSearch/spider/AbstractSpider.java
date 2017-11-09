package cn.mayzan.mBtSearch.spider;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.mayzan.mBtSearch.entity.BtInfo;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public abstract class AbstractSpider implements PageProcessor {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected Site site = Site.me().setRetryTimes(3).setSleepTime(5000)
			.setUserAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

	/**
	 * 总数,默认100
	 */
	protected int total = 100;

	/**
	 * 查询关键词
	 */
	protected String key = "";

	/**
	 * 查询结果集
	 */
	protected List<BtInfo> resultList;

	public AbstractSpider(String key, int total, List<BtInfo> resultList) {
		this.resultList = resultList;
		if (key != null && !"".equals(key)) {
			this.key = key;
		}
		if (total > 0) {
			this.total = total;
		}
	}

	public abstract void run();

	@Override
	public abstract void process(Page page);

	@Override
	public Site getSite() {
		return site;
	}
}
