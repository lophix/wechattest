package com.util;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * xml javabean转换
 *
 * @Authuor Administrator
 * @Create 2016-09-07-17:14
 */
public class XmlBeanTransfer {

    public static <T> String beanToXml(T obj) {
        long lasting = System.currentTimeMillis();//效率检测
        String result = null;
        try {
            Document document = DocumentHelper.createDocument();
            Element root = document.addElement("xml");//添加根节点
            Field[] properties = obj.getClass().getDeclaredFields();//获得实体类的所有属性

            //递归实体
            for (int i = 0; i < properties.length; i++) {
                //反射get方法
                Method meth = obj.getClass().getMethod("get"
                        + properties[i].getName().substring(0, 1)
                        .toUpperCase()
                        + properties[i].getName().substring(1));
                //为二级节点添加属性，属性值为对应属性的值
                root.addElement(properties[i].getName()).setText(
                        meth.invoke(obj).toString());
            }
            //生成XML文件
            result = document.asXML();
            long lasting2 = System.currentTimeMillis();
            System.out.println("写入XML文件结束,用时" + (lasting2 - lasting) + "ms");
        } catch (Exception e) {
            System.out.println("XML文件写入失败");
        }
        return result;
    }

    public static <T> List<T> xmlToBean(T obj, String xml) {
        long lasting = System.currentTimeMillis();  //检测效率
        List<T> list = new ArrayList<>();  //创建集合
        Document document = null;
        try {
            SAXReader reader = new SAXReader();
            document = DocumentHelper.parseText(xml);  //字符串转xml文件
            Element root = document.getRootElement();  //获取根节点
            Element foo = null;  //二级节点
            Field[] properties = obj.getClass().getDeclaredFields();  //获得实例的属性
            Method setMethod;   //实例的set方法
            //遍历obj.getClass().getSimpleName()节点
            Iterator iterator = root.elementIterator();
            for (; iterator.hasNext(); ) {
                foo = (Element) iterator.next();  //下一个二级节点
                obj = (T) obj.getClass().newInstance();  //获取对象的新实例
                for (int j = 0; j < properties.length; j++) {  //遍历子孙节点
                    //实例的set方法
                    setMethod = obj.getClass().getMethod("set"
                            + properties[j].getName().substring(0, 1).toUpperCase()
                            + properties[j].getName().substring(1), properties[j].getType());  //获得实例的set方法，通过方法名
                    setMethod.invoke(obj, foo.elementText(properties[j].getName()));  //调用set方法
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("xml转javabean结束，用时：" + (end - lasting) + " ms");
        return list;
    }
}
