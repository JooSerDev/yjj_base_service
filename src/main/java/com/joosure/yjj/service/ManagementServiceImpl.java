package com.joosure.yjj.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.entity.domain.Result;
import com.joosure.service.api.ManagementService;
import com.joosure.yjj.dao.redis.ManagementRedisDao;

/**
 * 管理端API
 * 
 * @author shawn
 *
 */
public class ManagementServiceImpl implements ManagementService {

	@Autowired
	private SQLservice sqLservice;
	@Autowired
	private ManagementRedisDao managementRedisDao;

	/**
	 * 修改总额接口
	 */
	@Override
	public Result setAmountTotal(Integer isUsingSetting, String amount) {
		Result result = null;
		// 校验
		if (isUsingSetting == null || amount == null || isUsingSetting > 1 || isUsingSetting < 0 || amount.equals("")) {
			result = new Result(2000, "输入参数有误");
		}

		// 数据库操作
		sqLservice.saveTotalAmountSettings(isUsingSetting, amount);

		// redis操作
		if (isUsingSetting.equals(0)) {
			String tAmount = sqLservice.getRealTotalAmount();
			managementRedisDao.setTotalAmount(tAmount);
		} else {
			managementRedisDao.setTotalAmount(amount);
		}

		result = new Result(0);
		return result;
	}

}
