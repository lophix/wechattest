
import com.bean.TextMessage;
import com.service.TulingApiProcess;
import com.util.MessageUtil;
import com.util.MyHttpRequest;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.Date;

/**
 * junit测试
 *
 * @Authuor Administrator
 * @Create 2016-09-09-9:14
 */
public class MyTest {
    @Test
    public void test_textMessageToXml(){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName("hello");
        textMessage.setFromUserName("你好");
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        textMessage.setContent("good morning");
        System.out.println(MessageUtil.textMessageToXml(textMessage));
    }

    @Test
    public void test_processRequest() throws DocumentException {
        SAXReader reader = new SAXReader();
        //String result = MyHttpRequest.sendPost("http://123.56.223.112/wechattest_war/wechat.do",reader.read(new File("wechat.xml")).getRootElement().asXML());
        String result = MyHttpRequest.sendPost("http://127.0.0.1:8080/wechat.do",reader.read(new File("wechat.xml")).getRootElement().asXML());
        System.out.println(result);
    }

    @Test
    public void test_getTulingResult(){
        System.out.println(TulingApiProcess.getTulingResult("good morning"));
    }
}
