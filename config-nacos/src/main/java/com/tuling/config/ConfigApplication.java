package com.tuling.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConfigApplication.class, args);
        String userName=applicationContext.getEnvironment().getProperty("user.name");
        String userAge=applicationContext.getEnvironment().getProperty("user.age");
        String config=applicationContext.getEnvironment().getProperty("user.config");
        System.err.println("user name："+userName+"； age："+userAge +"; config:"+config);

        //nacos客户端每10ms去注册中心进行判断，根据md5
    }
}
