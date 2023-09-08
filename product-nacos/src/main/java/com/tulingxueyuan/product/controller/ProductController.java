package com.tulingxueyuan.product.controller;

import cn.hutool.json.JSONUtil;
import com.tulingxueyuan.order.base.ApiResponse;
import com.tulingxueyuan.order.entity.SupplierMobileHistoryAdminRes;
import com.tulingxueyuan.order.entity.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tencent/product")
public class ProductController {

    @Value("${server.port}")
    String port;

    @RequestMapping("/{id}")
    public String get(@PathVariable("id") Integer id) throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("查询商品"+id);
        return "查询商品"+id+":"+port;
    }

    @GetMapping("/{supplierId}")
    public ApiResponse<List<SupplierMobileHistoryAdminRes>> aaa(@PathVariable("supplierId") Integer  supplierId){
        SupplierMobileHistoryAdminRes supplierMobileHistoryAdminRes1=new SupplierMobileHistoryAdminRes();
        supplierMobileHistoryAdminRes1.setMobileTag(1);
        supplierMobileHistoryAdminRes1.setSupplierId(2143123);
        supplierMobileHistoryAdminRes1.setUserName("张三");

        SupplierMobileHistoryAdminRes  supplierMobileHistoryAdminRes2=new SupplierMobileHistoryAdminRes();
        supplierMobileHistoryAdminRes2.setMobileTag(2);
        supplierMobileHistoryAdminRes2.setSupplierId(2143123);
        supplierMobileHistoryAdminRes2.setUserName("李四");

        SupplierMobileHistoryAdminRes  supplierMobileHistoryAdminRes3=new SupplierMobileHistoryAdminRes();
        supplierMobileHistoryAdminRes3.setMobileTag(3);
        supplierMobileHistoryAdminRes3.setSupplierId(2143123);
        supplierMobileHistoryAdminRes3.setUserName("王五");

        List<SupplierMobileHistoryAdminRes> list=new ArrayList<>();
        list.add(supplierMobileHistoryAdminRes1);
        list.add(supplierMobileHistoryAdminRes2);
        list.add(supplierMobileHistoryAdminRes3);

        ApiResponse result =   ApiResponse.ok(list);
//        System.out.println(JSONUtil.toJsonStr(res));
//        return    JSONUtil.toJsonStr(res);
 //       Result<List<SupplierMobileHistoryAdminRes>> result = new Result<>(200, "success", list);
        //System.out.println(JSONUtil.toJsonStr(result));
   //     System.out.println("12312312431241243123123");
        return  result;

    }





    @GetMapping("/pull")
    public  ApiResponse  pull(){
        ApiResponse result =   ApiResponse.ok("200","获取成功......");
        return  result;
    }

    @GetMapping("/district/pull")
    public boolean districtPull(){
        ApiResponse result =   ApiResponse.ok("300","获取成功......");

        return   ApiResponse.isSuccess(result);
    }



    @GetMapping("/userid/{userId}")
    public ApiResponse<UserDTO> userId(@PathVariable("userId") Integer  userId){
        UserDTO userDTO=new UserDTO();
        userDTO.setWxUserId("111111");
        userDTO.setNickname("nihaohaohao");
        return  ApiResponse.ok("成功",userDTO);
    }



    @GetMapping("/test")
    public  String  test(){
        System.out.println("12312312431241243123123");
        return  "12312312431241243123123";
    }


    public static void main(String[] args) {
        ProductController aa=new ProductController();
         System.out.println(JSONUtil.toJsonStr(aa.aaa(1)));
    }
}
