package cn.mayzan.mBtSearch.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mayzan.mBtSearch.common.ResponseResult;
import cn.mayzan.mBtSearch.entity.BtInfo;
import cn.mayzan.mBtSearch.service.BtSpiderService;

@Controller
public class BtSearchController {

	@Autowired
	private BtSpiderService spiderService;

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}

	@RequestMapping(value = "/list/{key}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseResult listSearchResult(@PathVariable("key") String key,
			@RequestParam(required = false, defaultValue = "10") int pageSize,
			@RequestParam(required = false, defaultValue = "1") int page) {
		int requiredNumber = page * pageSize;
		List<BtInfo> result = spiderService.searchBt(key, page, pageSize);
		return ResponseResult.ok(result);
	}
}
