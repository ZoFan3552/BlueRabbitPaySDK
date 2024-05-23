package com.zeddic.factory.defaults;

import com.zeddic.factory.Configuration;
import com.zeddic.factory.PayFactory;
import com.zeddic.payments.nativepay.INativePay;
import com.zeddic.payments.nativepay.service.NativePayService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class DefaultPayFactory implements PayFactory {
    private final Configuration configuration;
    private final OkHttpClient okHttpClient;

    public DefaultPayFactory(Configuration configuration) {
        this.configuration = configuration;
        //1.日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLogLevel());
        //2.开启okhttp客户端
        this.okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();
    }

    @Override
    public NativePayService createNativePayService() {
        //1.构建API
        INativePay nativePay = new Retrofit.Builder()
                .baseUrl(configuration.getApiHost())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePay.class);
        //2.创建支付服务
        return new NativePayService(configuration, nativePay);
    }
}
