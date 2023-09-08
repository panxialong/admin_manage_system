package com.tulingxueyuan.order.controller;


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

    @RequestMapping("/add")
    public String add(){
        String msg = stockFeignService.reduct2();
        return "Hello Feign"+msg;
    }
}
