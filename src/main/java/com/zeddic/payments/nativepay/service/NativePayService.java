package com.zeddic.payments.nativepay.service;

import com.zeddic.factory.Configuration;
import com.zeddic.payments.nativepay.INativePay;
import com.zeddic.payments.nativepay.model.PrepayRequest;
import com.zeddic.payments.nativepay.model.PrepayResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class NativePayService {
    private final INativePay nativePay;
    private final Configuration configuration;

    public NativePayService(Configuration configuration, INativePay nativePay) {
        this.configuration = configuration;
        this.nativePay = nativePay;
    }

    public PrepayResponse prepay(PrepayRequest prepayRequest) throws IOException {
        //1.请求接口和签名
        Call<PrepayResponse> prepay = nativePay.prepay(
                prepayRequest.getMchId(),
                prepayRequest.getOutTradeNo(),
                prepayRequest.getTotalFee(),
                prepayRequest.getBody(),
                prepayRequest.getTimestamp(),
                prepayRequest.getNotifyUrl(),
                prepayRequest.createSign(configuration.getPartnerKey()));
        Response<PrepayResponse> response = prepay.execute();
        return response.body();
    }
}
