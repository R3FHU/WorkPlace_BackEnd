package org.example.music_backend.music.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPooled;


@Configuration
public class Redis_Config {
    @Bean
    public JedisPooled jedisPooled() {
        return new JedisPooled("localhost", 6379);
    }
}
