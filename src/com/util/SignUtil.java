package com.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 验证工具类
 *
 * @Authuor Administrator
 * @Create 2016-09-08-9:17
 */
public class SignUtil {
    private static final String TOKEN = "wechatjava";

    public static boolean checkSignature(String signature, String timestamp, String nonce) throws NoSuchAlgorithmException {
        StringBuffer sb = new StringBuffer();
        ArrayList<String> list = new ArrayList<>();
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        for (int i = 0; i < 3; i++) {
            sb.append(list.get(i));
        }
        String sbstr = sb.toString();
        //SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(sbstr.getBytes());
        byte[] digest = md.digest();
        StringBuffer hexStr = new StringBuffer();
        String shaHex = "";
        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if(shaHex.length() < 2)
                hexStr.append(0);
            hexStr.append(shaHex);
        }
        String result = hexStr.toString();
        if(signature.equals(result))
            return true;
        else
            return false;
    }
}
