package com.tulingxueyuan.order.intercptor.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomFeignInterceptor  implements RequestInterceptor {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //Todo
        requestTemplate.header("xxxx","xxx");
        requestTemplate.query("id","1111");
        requestTemplate.uri("/9");
    }
}
