package com.joosure.yjj.dao.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.joosure.yjj.constants.RedisKeyConstants;
import com.shawn.server.core.redis.RedisGeneratorDao;

@Repository(value = "websiteRedisDao")
public class WebsiteRedisDao extends RedisGeneratorDao<String, String> {

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

}
