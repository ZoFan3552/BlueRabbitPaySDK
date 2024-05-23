package com.zeddic.factory;

import com.zeddic.payments.app.AppPayService;
import com.zeddic.payments.h5.H5PayService;
import com.zeddic.payments.jsapi.JSPayService;
import com.zeddic.payments.jump_h5.JumpH5PayService;
import com.zeddic.payments.nativepay.service.NativePayService;

/**
 * 支付工厂
 */
public interface PayFactory {
    NativePayService createNativePayService();

    H5PayService h5PayService();

    AppPayService appPayService();

    JSPayService jsPayService();

    JumpH5PayService jumpH5PayService();
}
