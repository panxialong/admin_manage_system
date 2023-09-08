package com.tulingxueyuan.order.base;


import lombok.ToString;

/*
 * API响应码(持续补充完善)
 *
 * @author zhanghao@ubenchuxing.com
 * @date 2022/03/19
 * */
@ToString
public enum ApiResponseCode implements IApiResponseCode {

    // 状态码
    SUCCESS(200, "success"),
    FAILED(500, "error"),
    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    UNAUTHORIZED(401, "身份认证失败"),
    FORBIDDEN(403, "服务器拒绝该次访问"),
    // 请求超时
    GATEWAY_TIME_OUT(504, "请求超时"),

    //业务码
    COMMON_FAILED_CODE(10000, "通用错误码"),
    API_ILLEGAL_PARAEMTER(10001, "不合法参数"),
    PARAMS_NOT_NULL(10002, "参数不能为空"),
    REDIS_ERROR(10100, "Redis开关配置异常"),

    //支付分
    PAYSCORE_PERMISSION_OPENED(10200, "用户已授权"),
    PAYSCORE_PERMISSION_CLOSED(10201, "支付分授权已关闭"),
    PAYSCORE_ORDER_PAID(10202, "支付分订单已支付"),

    ;

    public int code;
    public String message;

    ApiResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    //根据code获取枚举
    public static String getMessage(int code) {
        for (ApiResponseCode temp : ApiResponseCode.values()) {
            if (temp.getCode() == code) {
                return temp.message;
            }
        }
        return null;
    }
}

