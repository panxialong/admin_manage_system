package com.tulingxueyuan.order.common;


import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tulingxueyuan.order.base.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class HttpRequestCore {
    @Autowired
    private RestTemplate restTemplate;

    //@Value("${apiserver.key}")
   // @Value("321312431243124123")
    private String tencentKey;

    //@Value("${apiserver.url}")
    @Value("tencent")
    private String apiUrlBase;


    /**
     * 发送http请求，获取ApiResponse<T>
     *
     * @param httpRequestConfig 请求相关信息
     * @param responseType      响应的实体类型
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ApiResponse<T> callApiResponse(HttpRequestConfig httpRequestConfig, Class<T> responseType) throws Exception {
        String jsonString = this.sendRequest(
                httpRequestConfig.getPath(),
                httpRequestConfig.getBody(),
                httpRequestConfig.getUriVariables(),
                String.class,
                httpRequestConfig.getHttpMethod(),
                httpRequestConfig.isEnableLog(),
                httpRequestConfig.getOuterHeader(),
                httpRequestConfig.getDefautlAddUserToHeader());
        ApiResponse<T> result = this.getApiResponse(responseType, jsonString);
        return result;
    }

    /**
     * 发送请求,支持get/post/put/delete
     *
     * @param httpRequestConfig
     * @param typeReference      demo:new TypeReference<ApiResponse<List<OrderRes>>>(){}
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T callApiResponseByType(HttpRequestConfig httpRequestConfig, TypeReference<T> typeReference) throws Exception {
        String resultStr = this.sendRequest(
                httpRequestConfig.getPath(),
                httpRequestConfig.getBody(),
                httpRequestConfig.getUriVariables(),
                String.class,
                httpRequestConfig.getHttpMethod(),
                httpRequestConfig.isEnableLog(),
                httpRequestConfig.getOuterHeader(),
                httpRequestConfig.getDefautlAddUserToHeader());
        T result = JSONObject.parseObject(resultStr, typeReference);
        return result;
    }

    /**
     * 发送请求,支持get/post/put/delete
     *
     * @param httpRequestConfig
     * @param parameterizedTypeReference      demo:new ParameterizedTypeReference<ApiResponse<List<OrderRes>>>(){}
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> T callApiResponseByType(HttpRequestConfig httpRequestConfig, ParameterizedTypeReference<T> parameterizedTypeReference) throws Exception {
        T result = this.sendRequest(
                httpRequestConfig.getPath(),
                httpRequestConfig.getBody(),
                httpRequestConfig.getUriVariables(),
                parameterizedTypeReference,
                httpRequestConfig.getHttpMethod(),
                httpRequestConfig.isEnableLog(),
                httpRequestConfig.getOuterHeader(),
                httpRequestConfig.getDefautlAddUserToHeader());
        return result;
    }

    /**
     * 发送http请求，获取ApiResponse<T>
     *
     * @param path         请求路径
     * @param body         请求body
     * @param uriVariables 请求参数
     * @param responseType 响应类型
     * @param httpMethod   请求Method
     * @param enableLog    是否启用内部记录日志
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> ApiResponse<T> callApiResponse(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, HttpMethod httpMethod, boolean enableLog) throws Exception {
        String jsonString = this.sendRequest(path, body, uriVariables, String.class, httpMethod, enableLog);
        ApiResponse<T> result = getApiResponse(responseType, jsonString);
        return result;
    }

    /*
     * 拼装api结果
     * 使用jackson封装ApiResponse<T>并返回
     */
    private <T> ApiResponse<T> getApiResponse(Class<T> apiDataType, String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);
            Integer apiResultCode = rootNode.get("code").asInt();
            String apiResultMessage = rootNode.get("message").asText();
            T apiResultData = null;
            if (rootNode.hasNonNull("data")) {
                JsonNode dataNode = rootNode.at("/data");
                apiResultData = objectMapper.treeToValue(dataNode, apiDataType);
            }
            ApiResponse<T> result = new ApiResponse<>(apiResultCode, apiResultMessage, apiResultData);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
     *发送请求,支持get/post/put/delete
     */
    public <T> T sendRequest(HttpRequestConfig httpRequestConfig, Class<T> responseType) throws Exception {
        return this.sendRequest(httpRequestConfig.getPath(), httpRequestConfig.getBody(), httpRequestConfig.getUriVariables(), responseType, httpRequestConfig.getHttpMethod(), httpRequestConfig.isEnableLog(), httpRequestConfig.getOuterHeader(), httpRequestConfig.getDefautlAddUserToHeader());
    }

    /*
     *发送post请求
     */
    public <T> T sendPost(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, boolean enableLog) throws Exception {
        return this.sendRequest(path, body, uriVariables, responseType, HttpMethod.POST, enableLog);
    }

    /*
     *发送put请求
     */
    public <T> T sendPut(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, boolean enableLog) throws Exception {
        return this.sendRequest(path, body, uriVariables, responseType, HttpMethod.PUT, enableLog);
    }

    /*
     *发送get请求
     */
    public <T> T sendGet(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, boolean enableLog) throws Exception {
        return this.sendRequest(path, body, uriVariables, responseType, HttpMethod.GET, enableLog);
    }

    /*
     *发送delete请求
     */
    public <T> T sendDelete(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, boolean enableLog) throws Exception {
        return this.sendRequest(path, body, uriVariables, responseType, HttpMethod.DELETE, enableLog);
    }

    /*
     *发送post请求
     */
    public <T> T sendPost(String path, Object body, Class<T> responseType) throws Exception {
        return this.sendRequest(path, body, null, responseType, HttpMethod.POST, false);
    }

    /*
     *发送put请求
     */
    public <T> T sendPut(String path, Object body, Class<T> responseType) throws Exception {
        return this.sendRequest(path, body, null, responseType, HttpMethod.PUT, false);
    }

    /*
     *发送get请求
     */
    public <T> T sendGet(String path, Map<String, ?> uriVariables, Class<T> responseType) throws Exception {
        return this.sendRequest(path, null, uriVariables, responseType, HttpMethod.GET, false);
    }

    /*
     *发送delete请求
     */
    public <T> T sendDelete(String path, Map<String, ?> uriVariables, Class<T> responseType) throws Exception {
        return this.sendRequest(path, null, uriVariables, responseType, HttpMethod.DELETE, false);
    }

    /*
     *发送请求
     */
    private <T> T sendRequest(String path, Object body, Map<String, ?> uriVariables, Class<T> responseType, HttpMethod httpMethod, boolean enableLog) throws Exception {
        return this.sendRequest(path, body, uriVariables, responseType, httpMethod, enableLog, null, false);
    }

    private <T> T sendRequest(String path,
                              Object body,
                              Map<String, ?> uriVariables,
                              Class<T> responseType,
                              HttpMethod httpMethod,
                              boolean enableLog,
                              Map<String, String> header,
                              boolean defautlAddUserToHeader
    ) throws Exception {
        String requestUrl = checkUrl(path);
        HttpHeaders headers = getHttpHeaders(header, defautlAddUserToHeader);
        InnerLog innerLog = new InnerLog(enableLog);
        HttpEntity entity = new HttpEntity<>(body, headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (uriVariables == null) {
            uriVariables = new HashMap<>();
        }
        ResponseEntity<T> resResult = this.restTemplate.exchange(requestUrl, httpMethod, entity, responseType, uriVariables);
        T responseData = resResult.getBody();
        innerLog.logRespose(path, responseData);
        return responseData;
    }

    private <T> T sendRequest(String path,
                              Object body,
                              Map<String, ?> uriVariables,
                              ParameterizedTypeReference<T> responseType,
                              HttpMethod httpMethod,
                              boolean enableLog,
                              Map<String, String> header,
                              boolean defautlAddUserToHeader
    ) throws Exception {
        //String requestUrl = checkUrl(path);
        HttpHeaders headers = getHttpHeaders(header, defautlAddUserToHeader);
        InnerLog innerLog = new InnerLog(enableLog);
        HttpEntity entity = new HttpEntity<>(body, headers);
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (uriVariables == null) {
            uriVariables = new HashMap<>();
        }
        ResponseEntity<T> resResult = this.restTemplate.exchange(path, httpMethod, entity, responseType, uriVariables);
        T responseData = resResult.getBody();
        innerLog.logRespose(path, responseData);
        return responseData;
    }

    /**
     * 获取请求headers信息。内部适配腾讯api签名
     *
     * @param outerHeader            外部header信息
     * @param defautlAddUserToHeader 是否默认提取用户信息放入header
     * @return
     * @throws Exception
     */
    private HttpHeaders getHttpHeaders(Map<String, String> outerHeader, boolean defautlAddUserToHeader) throws Exception {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String signStr = "tencent-ts=" + timestamp + "&tencent-key=" + this.tencentKey;
        byte[] byteArray = signStr.getBytes("UTF-8");
        String sign = DigestUtils.md5DigestAsHex(byteArray);
        HttpHeaders headers = new HttpHeaders();
        headers.add("tencent-ts", timestamp);
        headers.add("tencent-sign", sign);
        if (defautlAddUserToHeader) {
            try {
                //LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
                headers.add("user", JSONUtil.toJsonStr(null));
            } catch (Exception e) {
                log.error("HttpRequestCore Header处理用户信息异常", e);
                throw new Exception(e);
            }
        }
        if (null != outerHeader && outerHeader.size() > 0) {
            for (Map.Entry<String, String> entry : outerHeader.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return headers;
    }

    /*
     * 检查传入url or path
     */
    private String checkUrl(String path) throws Exception {
        if (!StringUtils.hasLength(path)) {
            throw new Exception("path不能为空");
        }
        if (StringUtils.startsWithIgnoreCase(path, "http")) {
            return path;
        }
        if (!StringUtils.startsWithIgnoreCase(path, "/")) {
            path = "/".concat(path);
        }
        path = this.apiUrlBase.concat(path);
        return path;
    }

    /*
     * Map转Get请求参数
     * */
    public String urlParamsByMap(Map<String, Object> map) throws Exception {
        if (map == null || map.size() == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        sb.append("?");
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    class InnerLog {

        private boolean enableLog = false;
        private UUID traceID;

        public InnerLog(boolean _enableLog) {
            this.enableLog = _enableLog;
            if (enableLog)
                traceID = UUID.randomUUID();
        }

        void logRequest(String path, Object body) {
            if (enableLog) {
                log.info("traceID {} 请求日志[HttpRequestCore] path:{}  params:{} version{}", traceID, path, JSONUtil.toJsonStr(body), 10000010);
            }
        }

        <T> void logRespose(String path, T responseData) {
            if (enableLog) {
                log.info("traceID {} 请求日志[HttpRequestCore] path:{}  response:{}", traceID, path, JSONUtil.toJsonStr(responseData));
            }
        }
    }
}


