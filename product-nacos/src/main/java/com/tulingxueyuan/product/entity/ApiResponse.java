package com.tulingxueyuan.product.entity;


import cn.hutool.json.JSONUtil;
import com.tulingxueyuan.order.base.ApiResponseCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 优本内部服务返回结果对象
 *
 * @author zhanghao@ubenchuxing.com
 * @date 2022/03/19
 */
@ApiModel(value = "返回结果对象")
public class ApiResponse<T> implements Serializable {

    /**
     * 编码：200表示成功，其他值表示失败
     */
    @ApiModelProperty(value = "编码：200表示成功，其他值表示失败")
    private int code = 200;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String message = "success";
    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int code) {
        this.code = code;
        this.message = ApiResponseCode.getMessage(code);
    }

    public ApiResponse(String message) {
        this.code = ApiResponseCode.SUCCESS.getCode();
        this.message = message;
    }

    public ApiResponse(T data) {
        this.code = ApiResponseCode.SUCCESS.getCode();
        this.message = ApiResponseCode.SUCCESS.getMessage();
        this.data = data;
    }

    public ApiResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> ok() {
        return new ApiResponse();
    }

    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse(data);
    }

    public static <T> ApiResponse<T> ok(int code, String msg) {
        return new ApiResponse(code, msg);
    }

    public static <T> ApiResponse<T> ok(String msg, T data) {
        return new ApiResponse(ApiResponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ApiResponse<T> ok(int code, String message, T data) {
        return new ApiResponse(code, message, data);
    }

    public static <T> ApiResponse<T> failed() {
        return new ApiResponse(ApiResponseCode.FAILED.getCode(), ApiResponseCode.FAILED.getMessage());
    }

    public static <T> ApiResponse<T> failed(int code) {
        return new ApiResponse(code, ApiResponseCode.getMessage(code));
    }

    public static <T> ApiResponse<T> failed(String message) {
        return new ApiResponse(ApiResponseCode.FAILED.getCode(), message);
    }

    public static <T> ApiResponse<T> failed(int code, String message) {
        return new ApiResponse(code, message);
    }

    public boolean success() {
        return code == 200 ? true : false;
    }

    public static boolean isSuccess(ApiResponse response){
        return response != null && response.success();
    }

    public static boolean isSuccessData(ApiResponse response){
        return response != null && response.success() && response.getData() != null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        ApiResponse res = ApiResponse.ok();
        System.out.println(JSONUtil.toJsonStr(res));
    }
}
