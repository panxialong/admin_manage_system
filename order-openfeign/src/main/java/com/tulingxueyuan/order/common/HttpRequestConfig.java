package com.tulingxueyuan.order.common;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestConfig {
    /**
     * 请求路径
     */
    private String path;
    /**
     * 是否启用内部日志记录
     */
    @Builder.Default
    private boolean enableLog = false;
    /**
     * 请求body
     */
    private Object body;
    /**
     * 请求参数
     */
    private Map<String, ?> uriVariables;
    /**
     * 请求Method
     */
    @Builder.Default
    private HttpMethod httpMethod = HttpMethod.GET;
    /**
     * 是否请求处理类内部添加用户信息到header
     */
    @Builder.Default
    private Boolean defautlAddUserToHeader = true;
    /**
     * 请求处理类添加外部调用传入的header信息10000010
     */
    private Map<String,String> outerHeader;
}