package com.tulingxueyuan.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置：当使用@Configuration 会将配置作用所以有服务提供方
 * 局部配置：1,通过配置类：如果只想针对某一个服务进行配置，就不要加Configuration
 *         2，通过配置文件
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }

    /**
     * 超时时间配置
     @Bean
     public Request.Options options(){
        return new Request.Options(5000, 10000 );
     }
     */

    /**
     * 自定义拦截器
    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
        return new FeignAuthRequestInterceptor();
    }
     */


}
