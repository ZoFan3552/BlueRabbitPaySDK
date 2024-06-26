package com.zeddic.payments.nativepay.service;

import com.zeddic.factory.Configuration;
import com.zeddic.payments.nativepay.INativePay;
import com.zeddic.payments.nativepay.model.*;
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

    /**
     * 查询订单
     *
     * @param request 请求入参
     * @return 订单信息
     * @throws Exception 异常
     */
    public QueryOrderByOutTradeNoResponse queryOrderByOutTradeNo(QueryOrderByOutTradeNoRequest request) throws Exception {
        // 1. 请求接口 & 签名
        Call<QueryOrderByOutTradeNoResponse> call = nativePay.getPayOrder(
                request.getMchid(),
                request.getOutTradeNo(),
                request.getTimestamp(),
                request.createSign(configuration.getPartnerKey()));

        // 2. 获取数据
        retrofit2.Response<QueryOrderByOutTradeNoResponse> execute = call.execute();

        // 3. 返回结果
        return execute.body();
    }

    /**
     * 发起退单
     *
     * @param request 退单信息
     * @return 退单结果
     * @throws Exception 异常
     */
    public RefundOrderResponse refundOrder(RefundOrderRequest request) throws Exception {
        // 1. 请求接口 & 签名
        Call<RefundOrderResponse> call = nativePay.refundOrder(
                request.getMchid(),
                request.getOutTradeNo(),
                request.getOutRefundNo(),
                request.getTimestamp(),
                request.getRefundFee(),
                request.getRefundDesc(),
                request.getNotifyUrl(),
                request.createSign(configuration.getPartnerKey()));

        // 2. 获取数据
        retrofit2.Response<RefundOrderResponse> execute = call.execute();

        // 3. 返回结果
        return execute.body();
    }

    /**
     * 查询退单结果
     * @param request 请求参数
     * @return 退单信息
     * @throws Exception 异常
     */
    public GetRefundOrderResponse getRefundOrder(GetRefundOrderRequest request) throws Exception {
        // 1. 请求接口 & 签名
        Call<GetRefundOrderResponse> call = nativePay.getRefundOrder(
                request.getMchid(),
                request.getOutRefundNo(),
                request.getTimestamp(),
                request.createSign(configuration.getPartnerKey()));

        // 2. 获取数据
        retrofit2.Response<GetRefundOrderResponse> execute = call.execute();

        // 3. 返回结果
        return execute.body();
    }
}
