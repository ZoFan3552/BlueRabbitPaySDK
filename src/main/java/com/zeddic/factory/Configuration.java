package com.zeddic.factory;

import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Getter
public class Configuration {

    @Setter
    private String apiHost = "https://api.ltzf.cn/";

    private final String appID;

    private final String merchanID;

    private final String partnerKey;

    public Configuration(String appID, String merchanID, String partnerKey) {
        this.appID = appID;
        this.merchanID = merchanID;
        this.partnerKey = partnerKey;
    }

    @Setter
    private OkHttpClient okHttpClient;
    @Setter
    private HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.HEADERS;
    @Setter
    private long connectTimeout = 60;
    @Setter
    private long writeTimeout = 60;
    @Setter
    private long readTimeout = 60;

}
