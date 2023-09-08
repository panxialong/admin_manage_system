package com.tulingxueyuan.order.controller;


import com.tulingxueyuan.order.feign.ProductFeignService;
import com.tulingxueyuan.order.feign.StockFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    StockFeignService stockFeignService;

    @Autowired
    ProductFeignService productFeignService;
    @RequestMapping("/add")
    public String add(){
        String msg = stockFeignService.reduct();
        String p = productFeignService.get(1);




//        System.out.println("下单成功！");
//        String msg = restTemplate.getForObject("http://stock-service/stock/reduct", String.class);
        return "Hello Feign"+msg+p;
    }
}
