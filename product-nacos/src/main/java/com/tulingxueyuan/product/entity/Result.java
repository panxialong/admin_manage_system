package com.tulingxueyuan.product.entity;

import lombok.Data;

@Data
public class Result<T> {
    private int code; // 状态码
    private String message; // 消息
    private T data; // 数据

    public Result() {}

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // getters and setters
}