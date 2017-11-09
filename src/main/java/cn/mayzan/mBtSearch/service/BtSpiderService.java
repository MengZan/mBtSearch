package cn.mayzan.mBtSearch.service;

import java.util.List;

import cn.mayzan.mBtSearch.entity.BtInfo;

public interface BtSpiderService {

	/**
	 * 
	 * @param key
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<BtInfo> searchBt(String key, int page, int pageSize);
	
	/**
	 * 结果集排序
	 * @param sortBy 按什么排序
	 * @return
	 */
	public void sort(List<BtInfo> list, String sortBy);
}
