package com.zeddic.payments.nativepay.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)//防止接口变动而SDK解析不了新的参数
public class PrepayResponse {
    private Long code;
    private Data data;
    private String msg;
    @JsonProperty("request_id")
    private String requestId;

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data{
        @JsonProperty("code_url")
        private String codeUrl;
        @JsonProperty("QRcode_url")
        private String qrCodeUrl;
    }
}
