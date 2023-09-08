package com.tulingxueyuan.order.controller;


import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tulingxueyuan.order.base.ApiResponse;
import com.tulingxueyuan.order.common.HttpRequestConfig;
import com.tulingxueyuan.order.common.HttpRequestCore;
import com.tulingxueyuan.order.entity.CommonResult;
import com.tulingxueyuan.order.entity.SupplierMobileHistoryAdminRes;
import com.tulingxueyuan.order.entity.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author dsp
 * @date 2022年03月24日 16:09
 */
@Api(tags = "【管理系统】 运力商")
@RestController
@RequestMapping("v2/admin/supplier")
@SpringBootTest
@RunWith(SpringRunner.class)
public class SupplierDetailController {

//    @Resource
//    private SupplierFeignService supplierFeignService;

    @Autowired
    private HttpRequestCore httpRequestCore;



    @ApiOperation(value = "获取运力商虚拟号修改记录")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "supplierId", value = "运力商ID", dataType = "int", paramType = "query", required = true)
    })
//    @ApiResponses(
//        value = {
//            @ApiResponse(code = 200, message = "查询成功", response = SupplierMobileHistoryAdminRes.class),
//            @ApiResponse(code = 401, message = "身份认证失败", response = Integer.class),
//            @ApiResponse(code = 500, message = "服务器异常", response = Integer.class),
//            @ApiResponse(code = 10001, message = "不合法参数", response = Integer.class)
//        }
//    )
    @GetMapping("history/mobile")
    public  ApiResponse<List<SupplierMobileHistoryAdminRes>> historyMobile(@RequestParam Integer supplierId) {
        String url = "http://192.168.0.107:8023/tencent/product/";
        //String url = "http://product-nacos/product/";
        HttpRequestConfig httpRequestConfig=HttpRequestConfig.
                //builder().path("/v2/admin/supplier/history/mobile/"+supplierId).httpMethod(HttpMethod.GET).

                        builder().path(url+supplierId).httpMethod(HttpMethod.GET).
                build();
        try {
            ApiResponse<List<SupplierMobileHistoryAdminRes>>   res=httpRequestCore.callApiResponseByType(httpRequestConfig, new TypeReference<ApiResponse<List<SupplierMobileHistoryAdminRes>>>(){});
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // return supplierFeignService.historyMobile(supplierId);
    }



    @GetMapping("pull")
    public ApiResponse pull(){
        String url = "http://192.168.0.107:8023/tencent/product/pull";
        try {
            HttpRequestConfig httpRequestConfig=HttpRequestConfig.builder().path(url).httpMethod(HttpMethod.GET).build();
            ApiResponse  result=httpRequestCore.callApiResponse(httpRequestConfig, Object.class);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("district/pull")
    public ApiResponse<CommonResult> districtPull(){
        String url = "http://192.168.0.107:8023/tencent/product/district/pull";
        try {
            HttpRequestConfig httpRequestConfig=HttpRequestConfig.builder().path(url).httpMethod(HttpMethod.GET).build();
            ApiResponse<CommonResult>  result=httpRequestCore.callApiResponse(httpRequestConfig, CommonResult.class);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @GetMapping("userOrder")
    public ApiResponse<UserDTO> districtPull11(@RequestParam Long userId){
        String url = "http://192.168.0.107:8023/tencent/product/userid/";
        try {
            HttpRequestConfig httpRequestConfig=HttpRequestConfig.builder().path(url+userId).httpMethod(HttpMethod.GET).build();
            ApiResponse<UserDTO>  result=httpRequestCore.callApiResponse(httpRequestConfig, UserDTO.class);


            //System.out.println(result.getCode()+"------------------"+result.getData());
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }












//    @GetMapping("test")
//    public  String historyMobile1() {
//        String url = "http://192.168.0.107:8023/tencent/product/test";
//
//        HttpRequestConfig httpRequestConfig=HttpRequestConfig.
//                //builder().path("/v2/admin/supplier/history/mobile/"+supplierId).httpMethod(HttpMethod.GET).
//
//                        builder().path(url).httpMethod(HttpMethod.GET).
//                build();
//        try {
//            String  res=httpRequestCore.sendRequest(httpRequestConfig, String.class);
//            System.out.println("返回结果----------------："+res);
//            return res;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Test
    public void test(){
        UserDTO userDTO=new UserDTO();
        userDTO.setNickname("sda张三");
        userDTO.setWxUserId("1231314");
        userDTO.setId(4123341L);
        LocalDateTime aa= LocalDateTime.of(2018,1,13,9,43,20);
        userDTO.setCreateTime(null);
        userDTO.setEndTime(aa);

        if(userDTO.getCreateTime()!=null||userDTO.getEndTime()!=null){
            userDTO.setCreateTime(aa);
            userDTO.setEndTime(aa);
        }


//        Class clz = UserDTO.class;
//        try {
//            String str= mapToString(userDTO.getClass());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        try {
            String aaa=httpRequestCore.urlParamsByMap(objParse(userDTO));
            System.out.println(aaa);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }





    public static Map objParse( Object value) {
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map= objectMapper.convertValue(value, Map.class);
               return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
