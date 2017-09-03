package edu.school.config.others;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories("edu.school.dao")//使用Redis Repositories原因：Working with Redis Repositories allows
//to seamlessly convert and store domain objects in Redis Hashes, apply custom mapping strategies and make 
//use of secondary indexes.
public class RedisConfig {

	/**
	 * 配置redis
	 * @return
	 */
	@Bean
	public RedisConnectionFactory connectionFactory() {
		JedisConnectionFactory cf = new JedisConnectionFactory();
		cf.setHostName("localhost");
		cf.setPort(6379);
		return cf;
	}
	
	@Bean
	public RedisTemplate<?, ?> redisTemplate() {

	    RedisTemplate<byte[], byte[]> template = new RedisTemplate<byte[], byte[]>();
	    template.setConnectionFactory(connectionFactory());
	    return template;
	}

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		return new RedisCacheManager(redisTemplate);
	}
	
}
