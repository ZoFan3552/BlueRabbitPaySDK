package com.zeddic.factory;

import com.zeddic.payments.nativepay.service.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {
    NativePayService createNativePayService();
}
