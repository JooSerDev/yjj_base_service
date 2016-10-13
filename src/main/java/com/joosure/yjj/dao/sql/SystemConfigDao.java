package com.joosure.yjj.dao.sql;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joosure.entity.pojo.SystemConfig;

public interface SystemConfigDao {

	List<SystemConfig> getAll();

	SystemConfig getSystemConfig(@Param("sysKey") String key);

	void addSystemConfig(SystemConfig systemConfig);

	void updateSystemConfig(SystemConfig systemConfig);

}
