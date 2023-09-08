package com.tulingxueyuan.order.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@FeignClient(value="alibaba-stock-seata",path = "/stock")
public interface StockService {


    @RequestMapping("/reduct")
    public String reduct(@RequestParam(value="productId") Integer productId);
}
