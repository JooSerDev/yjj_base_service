package com.joosure.yjj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joosure.entity.pojo.SystemConfig;
import com.joosure.yjj.constants.SystemConfigKeyConstants;
import com.joosure.yjj.dao.sql.SystemConfigDao;

@Service
public class SQLservice {

	@Autowired
	private SystemConfigDao systemConfigDao;

	public void saveTotalAmountSettings(Integer isUsingSetting, String amount) {

		// 设置是否启用管理员设置的总额标志
		SystemConfig scFlag = systemConfigDao
				.getSystemConfig(SystemConfigKeyConstants.TOTAL_AMOUNT_SETTING_STATUS_FLAG);
		if (scFlag != null) {
			scFlag.setSysValue(isUsingSetting + "");
			systemConfigDao.updateSystemConfig(scFlag);
		} else {
			scFlag = new SystemConfig();
			scFlag.setSysKey(SystemConfigKeyConstants.TOTAL_AMOUNT_SETTING_STATUS_FLAG);
			scFlag.setSysValue(isUsingSetting + "");
			scFlag.setDescription("显示总额是否启用管理员设置的总额标志，0-否，1-是");
			systemConfigDao.addSystemConfig(scFlag);
		}

		// 管理员设置的总额
		SystemConfig scAmount = systemConfigDao.getSystemConfig(SystemConfigKeyConstants.TOTAL_AMOUNT_SETTING_NUMBER);
		if (scAmount != null) {
			scAmount.setSysValue(amount);
			systemConfigDao.updateSystemConfig(scAmount);
		} else {
			scAmount = new SystemConfig();
			scAmount.setSysKey(SystemConfigKeyConstants.TOTAL_AMOUNT_SETTING_NUMBER);
			scAmount.setSysValue(amount);
			scAmount.setDescription("显示总额中管理员设置的总额");
			systemConfigDao.addSystemConfig(scAmount);
		}
	}

	public String getRealTotalAmount() {
		SystemConfig sc = systemConfigDao.getSystemConfig(SystemConfigKeyConstants.TOTAL_AMOUNT_REAL_NUMBER);
		if (sc == null) {
			sc = new SystemConfig();
			sc.setSysKey(SystemConfigKeyConstants.TOTAL_AMOUNT_REAL_NUMBER);
			sc.setSysValue(0 + "");
			sc.setDescription("实际的总额");
			systemConfigDao.addSystemConfig(sc);
			return "0";
		} else {
			return sc.getSysValue();
		}
	}

}
