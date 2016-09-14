package com.joosure.yjj.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.joosure.entity.domain.Result;
import com.joosure.service.api.ManagementService;

public class ManagementServiceImpl implements ManagementService {

	@Autowired
	private SQLservice sqLservice;

	@Override
	public Result setAmountTotal(Integer isUsingSetting, String amount) {
		Result result = new Result();
		if (isUsingSetting == null || amount == null || isUsingSetting > 1 || isUsingSetting < 0 || amount.equals("")) {
			result.setErrCode(2000);
		}

		return result;
	}

}
