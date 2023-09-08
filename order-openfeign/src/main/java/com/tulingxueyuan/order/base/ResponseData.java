package com.tulingxueyuan.order.base;

import com.tulingxueyuan.order.enums.OrderBusinessStatus;
import lombok.Data;

/**
 * 响应成功或者失败状态类 该类应用于controller层,用于返回给前端响应状态,
 * 这是一个工具类,里面封装了程序运行成功响应给前端的状态码和内容和程序运行失败响应给前端的状态码普遍方法
 * 还有针对拥有特殊状态码的成功和失败的方法
 * @author Administrator
 * @param <T>
 */
@Data
public class ResponseData<T> {
    /**
     * 响应给前端的状态码
     */
    private int code;

    /**
     * 响应给前端的成功或者错误的内容
     */
    private String message;

    /**
     * 返回给前端的查询结果集
     */
    private T data;

    /**
     * 通用方法
     * 程序运行成功响应给前端的数据
     */
    public static <T> ResponseData<T> success() {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(OrderBusinessStatus.SUCCESS.getCode());
        responseBean.setMessage(OrderBusinessStatus.SUCCESS.getMessage());
        return responseBean;
    }

    /**
     * 通用方法
     * 程序运行成功响应给前端的数据
     */
    public static <T> ResponseData<T> success(T data) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(OrderBusinessStatus.SUCCESS.getCode());
        responseBean.setMessage(OrderBusinessStatus.SUCCESS.getMessage());
        responseBean.setData(data);
        return responseBean;
    }

    /**
     * 特殊方法
     * 针对程序运行成功时,拥有特殊状态码的响应
     */
    public static <T> ResponseData<T> success(OrderBusinessStatus status, T data) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(status.getCode());
        responseBean.setMessage(status.getMessage());
        responseBean.setData(data);
        return responseBean;
    }

    /**
     * 通用方法
     * 程序出现异常运行失败响应给前端的数据
     */
    public static <T> ResponseData<T> error(String msg) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setMessage(msg);
        responseBean.setCode(OrderBusinessStatus.Fail.getCode());
        return responseBean;
    }

    /**
     * 特殊方法
     * 针对程序运行成功时,拥有特殊状态码的响应
     */
    public static <T> ResponseData<T> error(OrderBusinessStatus status, T data) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(status.getCode());
        responseBean.setMessage(status.getMessage());
        responseBean.setData(data);
        return responseBean;
    }

    /**
     * 特殊方法
     * 针对程序运行成功时,拥有特殊状态码的响应
     */
    public static <T> ResponseData<T> error(OrderBusinessStatus status) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(status.getCode());
        responseBean.setMessage(status.getMessage());
        return responseBean;
    }

    /**
     * 特殊方法
     * 针对程序运行成功时,拥有特殊状态码的响应
     */
    public static <T> ResponseData<T> errorMessage(OrderBusinessStatus status, String  message) {
        ResponseData<T> responseBean = new ResponseData<>();
        responseBean.setCode(status.getCode());
        responseBean.setMessage(message);
        return responseBean;
    }

    /**
     * 判断响应是否成功
     * @param resp 响应结果
     * @return true-成功
     */
    public static <T> Boolean isSuccess(ResponseData<T> resp){
        return resp != null && OrderBusinessStatus.SUCCESS.getCode().equals(resp.getCode());
    }

    /**
     * 判断响应数据是否成功
     * @param resp 响应结果
     * @return true-成功
     */
    public static <T> Boolean isSuccessData(ResponseData<T> resp){
        return isSuccess(resp) && resp.getData() != null;
    }

    /**
     * 判断响应是否失败
     * @param resp 响应结果
     * @return true-失败
     */
    public static <T> Boolean isFail(ResponseData<T> resp){
        return resp == null || !OrderBusinessStatus.SUCCESS.getCode().equals(resp.getCode());
    }

    /**
     * 判断响应数据是否失败
     * @param resp 响应结果
     * @return true-失败
     */
    public static <T> Boolean isFailData(ResponseData<T> resp){
        return isFail(resp) || resp.getData() == null;
    }


}