package com.zeddic.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class SignUtils {

    public static String createSign(Map < String, String > params, String partnerKey) {
        // 生成签名前先去除sign
        params.remove("sign");
        String stringA = packageSign(params);
        String stringSignTemp = stringA + "&key=" + partnerKey;
        return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
    }

    private static String packageSign(Map< String, String > params) {
        // 先将参数以其参数名的字典序升序进行排序
        TreeMap< String, String > sortedParams = new TreeMap < String, String > (params);
        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry< String, String > param: sortedParams.entrySet()) {
            String value = param.getValue();
            if (StringUtils.isBlank(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=");
            sb.append(value);
        }
        return sb.toString();
    }

    private static String urlEncode(String src) throws UnsupportedEncodingException {
        return URLEncoder.encode(src, StandardCharsets.UTF_8.name()).replace("+", "%20");
    }

}
