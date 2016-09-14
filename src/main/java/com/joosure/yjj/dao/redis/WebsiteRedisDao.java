package com.joosure.yjj.dao.redis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.joosure.yjj.constants.RedisKeyConstants;
import com.shawn.server.core.redis.RedisGeneratorDao;

@Repository(value = "websiteRedisDao")
public class WebsiteRedisDao extends RedisGeneratorDao<String, String> {

	private static final Logger LOG = LoggerFactory.getLogger("redis_log");

	/**
	 * 取得前端显示的总额，若为空，返回0；
	 * 
	 * @return
	 */
	public String getTotalAmount() {
		String amount = redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(RedisKeyConstants.website_amount_total);
				byte[] value = connection.get(key);
				if (value == null) {
					return "0";
				}
				return serializer.deserialize(value);
			}

		});

		return amount;
	}

	/**
	 * 通过偏移量获得轮播数据，每次获取100条<br>
	 * 执行命令为：lrange 'website_data_list' 'offset' 'offset+100'
	 * 
	 * @param offset
	 * @return
	 */
	public List<String> getRollListDataByOffset(final long offset) {
		List<String> list = null;
		try {
			list = redisTemplate.opsForList().range(RedisKeyConstants.website_data_list, offset, offset + 100l);
		} catch (Exception e) {
			LOG.warn("WebsiteRedisDao.getRollListDataByOffset throw exception : " + e.getMessage());
		}
		if (list == null) {
			list = new ArrayList<>();
		}
		return list;
	}

}
