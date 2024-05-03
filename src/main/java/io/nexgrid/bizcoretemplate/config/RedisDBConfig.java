//package io.nexgrid.bizcoretemplate.config;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//@Configuration
//@RequiredArgsConstructor
//public class RedisDBConfig {
//
//    private final Environment env;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//        redisStandaloneConfiguration.setHostName(env.getProperty("spring.data.redis.host"));
//        redisStandaloneConfiguration.setPort(Integer.parseInt(env.getProperty("spring.data.redis.port")));
//        redisStandaloneConfiguration.setPassword(env.getProperty("spring.data.redis.password"));
//
//        return new LettuceConnectionFactory(redisStandaloneConfiguration);
//    }
//
//}
