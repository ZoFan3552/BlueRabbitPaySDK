package com.zeddic.test;

import com.alibaba.fastjson.JSON;
import com.zeddic.payments.nativepay.INativePay;
import com.zeddic.payments.nativepay.model.PrepayResponse;
import com.zeddic.util.SignUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ApiTest {
    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        Map<String , String> dataMap = new HashMap<>();
        dataMap.put("mch_id" , "1677728047");
        dataMap.put("out_trade_no" , "Zeddic20240523");
        dataMap.put("total_fee" , "0.01");
        dataMap.put("body" , "QQ公仔");
        dataMap.put("timestamp" , String.valueOf(timestamp));
        dataMap.put("notify_url" , "https://chat.zeddic.icu");
        String sign = SignUtils.createSign(dataMap, "cd43230d2720924143ccdfd1e83f4cdb");
        System.out.println(sign);

    }

    @Test
    public void test_retrofit2() throws IOException {
        OkHttpClient client = new OkHttpClient();

        INativePay nativePay = new Retrofit.Builder()
                .baseUrl("https://api.ltzf.cn/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(INativePay.class);

        long timestamp = System.currentTimeMillis() / 1000;
        System.out.println(timestamp);
        Map<String , String> dataMap = new HashMap<>();
        dataMap.put("mch_id" , "1677728047");
        dataMap.put("out_trade_no" , "Zeddic20240524");
        dataMap.put("total_fee" , "0.01");
        dataMap.put("body" , "QQ公仔");
        dataMap.put("timestamp" , String.valueOf(timestamp));
        dataMap.put("notify_url" , "https://chat.zeddic.icu");

        Call<PrepayResponse> prepay = nativePay.prepay(
                dataMap.get("mch_id"),
                dataMap.get("out_trade_no"),
                dataMap.get("total_fee"),
                dataMap.get("body"),
                dataMap.get("timestamp"),
                dataMap.get("notify_url"),
                SignUtils.createSign(dataMap, "cd43230d2720924143ccdfd1e83f4cdb"));
        Response<PrepayResponse> response = prepay.execute();
        Object body = response.body();

        log.info("测试结果：{}",JSON.toJSONString(body));
    }
}
