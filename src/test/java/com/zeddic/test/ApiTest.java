package com.zeddic.test;

import com.zeddic.util.SignUtils;

import java.util.HashMap;
import java.util.Map;

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
}
