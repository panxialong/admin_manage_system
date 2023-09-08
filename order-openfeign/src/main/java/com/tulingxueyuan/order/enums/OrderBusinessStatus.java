package com.tulingxueyuan.order.enums;

/**
 * @description 订单业务状态码
 * @author yxdsmile
 * @date 2022-03-18
 */
public enum OrderBusinessStatus {

    /**
     * 成功
     */
    SUCCESS(200, "成功"),
    /**
     * 服务异常
     */
    Fail(400, "服务异常"),
    /**
     * 未知错误
     */
    unknown(-1, "未知错误"),

    /**
     * 参数错误
     */
    PARAM_ERROR(-2, "参数错误"),

    /**
     * 数据异常
     */
    DATA_ERROR(-3, "数据异常"),

    /**
     * 存在未完成的订单
     */
    EXIST_NO_COMPLETE_ORDER(10000, "存在未完成的订单"),

    /**
     * 风控限制下单
     */
    RISK_LIMIT(10001, "风控限制下单"),

    /**
     * 无效订单号
     */
    INVALID_ORDER(10002, "无效订单号"),

    /**
     * 取消限制下单
     */
    NO_CANCEL_BY_COMPLETED_ORDER(10003, "以支付完结订单不能取消"),

    /**
     * 取消限制下单
     */
    NO_CANCEL_ORDER_BY_USER_THIS(10004, "行程开始以后，用户和平台不可以取消"),

    /**
     * 司机接单后才能轮询获取司机实时信息 
     */
    NO_QUERY_ORDER_DRIVER_LOCATION(10005, "司机接单后才能轮询获取司机实时信息"),

    /**
     * 未派单订单超过阈值
     */
    NO_ASSIGN_LIMIT(10006, "未派单订单超过阈值"),

    /**
     * 获取prepayId出错了
     */
    PREPAY_ID_ERROR(10007, "获取prepayId出错了"),

    /**
     * 预接单异常可重试
     */
    PRE_ACCEPTED_ABNORMAL_CAN_REPEAT(10008, "预接单异常可重试"),

    /**
     * 回调事件:无效
     */
    ORDER_CALLBACK_INVALID(11001, "回调事件:无效"),

    /**
     * 回调事件:业务异常
     */
    ORDER_ROLLBACK_ABNORMAL(11002, "回调事件:业务异常"),



    /**
     * 管理端操作订单：线下支付业务校验异常
     */
    ADMIN_OFFLINE_PAY_BUSINESS_ABNORMAL(12001, "线下支付业务校验异常"),

    /**
     * 订单非预改派状态
     */
    NON_PRE_REASSIGN(20001, "订单非预改派状态"),

    /**
     * 取消改派询价金额过高
     */
    CANCEL_REASSIGN_PRICE_TOO_HIGH(20002, "取消改派询价金额过高"),

    /**
     * 确认改派失败：订单状态校验异常
     */
    CONFIRM_REASSIGN_STATUS_ABNORMAL(20003, "确认改派失败：订单状态校验异常");

    private Integer code;
    private String message;

    OrderBusinessStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int code) {
        for (OrderBusinessStatus status : values()) {
            if (status.getCode().equals(code)) {
                return status.getMessage();
            }
        }
        return null;
    }
}
