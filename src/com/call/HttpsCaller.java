package com.call;

import com.util.CommonDataUtil;
import com.util.MyHttpRequest;
import net.sf.json.JSONObject;

/**
 * 调用微信接口，发送https协议的请求
 *
 * @Authuor Administrator
 * @Create 2016-09-02-10:10
 */
public class HttpsCaller {
    private static String access_token = null;
    private static long last;

    public static String getAccess_token() {
        access_token = getAcessToken();
        return access_token;
    }

    /**
     * 获取微信的Acess token
     */
    public static String getAcessToken(){
        String result = "";
        String expires_in = "";
        String errcode = "",errmsg = "";
        if(access_token == null) {
            if (last == 0 || last - (System.currentTimeMillis() / 1000) < 300) {
                String url = "https://api.weixin.qq.com/cgi-bin/token";
                String params = "grant_type=client_credential&appid=" + CommonDataUtil.APP_ID + "&secret=" + CommonDataUtil.APP_SECRET;
                String resp = MyHttpRequest.sendGet(url, params);
                JSONObject jsonObject = JSONObject.fromObject(resp);
                if (jsonObject.containsKey("access_token")) {
                    result = jsonObject.getString("access_token");
                    expires_in = jsonObject.getString("expires_in");
                    last = System.currentTimeMillis() / 1000 + Long.valueOf(expires_in);
                    System.out.println("凭证有效时间，单位：秒" + expires_in);
                } else if (jsonObject.containsKey("errcode")) {
                    errcode = jsonObject.getString("errcode");
                    errmsg = jsonObject.getString("errmsg");
                    System.out.println("错误码:" + errcode + "错误信息:" + errmsg);
                } else {
                    result = "请求异常";
                }
            }else{
                result = access_token;
            }
        }
        return result;
    }

    /**
     * 调用统一下单接口，获取预付款，prepay
     * @param payment
     * @param ip
     * @param code
     * @return
     */
    public static String getOrderNum(String payment, String ip, String code){
        String result = "";

        return result;
    }
}
