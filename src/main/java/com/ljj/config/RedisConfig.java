package com.ljj.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.text.SimpleDateFormat;

/**
 * @author Nevercircle
 * @version 1.0
 * @description: TODO
 * @date 2022/8/30 13:59
 */
public class RedisConfig extends CachingConfigurerSupport {
    @Bean
    public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, Object> template = new RedisTemplate<>();

        //ObjectMapper 指定在转成json的时候的一些转换规则
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //把自定义objectMapper设置到jackson2JsonRedisSerializer中（可以不设置，使用默认规则）
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //RedisTemplate默认的序列化方式使用的是JDK的序列化
        //设置了key的序列化方式
        template.setKeySerializer(new StringRedisSerializer());
        //设置了value的序列化方式
        template.setValueSerializer(jackson2JsonRedisSerializer);
        return template;
    }
}
