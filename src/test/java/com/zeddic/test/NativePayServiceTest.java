package com.zeddic.test;

import com.alibaba.fastjson.JSON;
import com.zeddic.factory.Configuration;
import com.zeddic.factory.PayFactory;
import com.zeddic.factory.defaults.DefaultPayFactory;
import com.zeddic.payments.nativepay.model.PrepayRequest;
import com.zeddic.payments.nativepay.model.PrepayResponse;
import com.zeddic.payments.nativepay.service.NativePayService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

@Slf4j
public class NativePayServiceTest {
    private  NativePayService nativePayService;

    @Before
    public void init(){
        Configuration configuration = new Configuration("zeddic" ,"1677728047","cd43230d2720924143ccdfd1e83f4cdb");
        PayFactory defaultPayFactory = new DefaultPayFactory(configuration);
        this.nativePayService = defaultPayFactory.createNativePayService();
    }

    @Test
    public void testPay() throws IOException {
        PrepayRequest request = new PrepayRequest();
        request.setMchId("1677728047");
        request.setOutTradeNo(String.valueOf(RandomStringUtils.randomNumeric(8)));
        request.setTotalFee("0.01");
        request.setBody("QQ公仔");
        request.setNotifyUrl("https://chat.zeddic.icu");

        PrepayResponse response = nativePayService.prepay(request);
        log.info("请求参数：{}" , JSON.toJSONString(request));
        log.info("响应结果：{}" , JSON.toJSONString(response));
    }

}
