package com.util;

import com.bean.XmlBean;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;

/**
 * XML文件和java对象转换工具类
 *
 * @Authuor Administrator
 * @Create 2016-09-02-13:44
 */
public class XMLBeanUtil {
    public static XmlBean XMLToBean(String result) {
        Serializer serializer = new Persister();
        XmlBean xml = new XmlBean();
        try {
            serializer.read(xml, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xml;
    }

    public static String BeanToXML(XmlBean myxml) {
        String result = "";
        Serializer serializer = new Persister();
        OutputStream out = new ByteArrayOutputStream();
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(out, "utf-8");
            serializer.write(myxml, writer);
            result = out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
