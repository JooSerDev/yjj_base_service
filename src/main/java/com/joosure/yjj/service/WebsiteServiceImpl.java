package com.joosure.yjj.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.service.api.WebsiteService;
import com.joosure.yjj.dao.redis.WebsiteRedisDao;

public class WebsiteServiceImpl implements WebsiteService {

	@Autowired
	private WebsiteRedisDao websiteRedisDao;

	@Override
	public String getAmountTotal() {
		// 直接从redis中读取
		return websiteRedisDao.getTotalAmount();
	}

}
