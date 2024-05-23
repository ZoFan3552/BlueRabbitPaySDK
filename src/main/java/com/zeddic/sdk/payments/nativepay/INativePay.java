package com.zeddic.sdk.payments.nativepay;

import retrofit2.Call;
import retrofit2.http.*;

/**
 *扫码支付API
 */
public interface INativePay {

    @POST("api/wxpay/native")
    @Headers("content-type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    Call<Object> prepay(@Field("mch_id") String mchId,
                        @Field("out_trade_no")String outTradeNo,
                        @Field("total_fee")String totalFee,
                        @Field("body")String body,
                        @Field("timestamp")String timestamp,
                        @Field("notify_url")String notifyUrl,
                        @Field("sign")String sign);
}
