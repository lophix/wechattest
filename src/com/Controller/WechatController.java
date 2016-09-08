package com.Controller;

import com.service.CoreService;
import com.util.SignUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.NoSuchAlgorithmException;

/**
 * 微信Controller
 *
 * @Authuor Administrator
 * @Create 2016-09-08-9:56
 */
@Controller
public class WechatController {
    @RequestMapping(value = "/wechat.do" ,method = RequestMethod.GET)
    @ResponseBody
    public String wechatcheck(String signature, String timestamp, String nonce, String echostr) throws NoSuchAlgorithmException {
        if (SignUtil.checkSignature(signature,timestamp,nonce))
            return echostr;
        else
            return  null;
    }

    @RequestMapping(value = "/wechat.do", method = RequestMethod.POST)
    @ResponseBody
    public String reply(HttpServletRequest request){
        String respMessage = CoreService.processRequest(request);//调用CoreService类的processRequest方法接收、处理消息，并得到处理结果；
        // 响应消息
        //调用response.getWriter().write()方法将消息的处理结果返回给用户
        return respMessage;
    }
}
