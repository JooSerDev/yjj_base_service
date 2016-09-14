package com.joosure.yjj.dao.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.joosure.yjj.constants.RedisKeyConstants;
import com.shawn.server.core.redis.RedisGeneratorDao;

public class ManagementRedisDao extends RedisGeneratorDao<String, String> {

	/**
	 * 设置总额
	 * 
	 * @param amount
	 * @return
	 */
	public boolean setTotalAmount(final String amount) {
		// redisTemplate.opsForValue().set(RedisKeyConstants.website_amount_total,
		// amount);

		if (amount == null || amount.equals("")) {
			return false;
		}

		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(RedisKeyConstants.website_amount_total);
				byte[] name = serializer.serialize(amount);

				return connection.setNX(key, name);
			}
		});

		return result;
	}

}
