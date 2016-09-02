package com.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * 发送URL请求的工具类
 *
 * @Authuor Administrator
 * @Create 2016-09-02-11:19
 */
public class MyHttpRequest {
    public static String sendPost(String url, String params) {
        String result = "";
        PrintWriter out = null;
        BufferedReader bufferedReader = null;
        try {
            URL realUrl = new URL(url);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.connect();
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream(), true);
            out.print(params);
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String resp;
            while ((resp = bufferedReader.readLine()) != null) {
                result += resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (out == null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, String params) {
        String result = "";
        String sendUrl = url + "?" + params;
        BufferedReader bufferedReader = null;
        try {
            URL realUrl = new URL(sendUrl);
            URLConnection urlConnection = realUrl.openConnection();
            urlConnection.setRequestProperty("accept", "*/*");
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
            urlConnection.setRequestProperty("Charsert", "UTF-8");
            urlConnection.connect();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
            String resp;
            while ((resp = bufferedReader.readLine()) != null) {
                result += resp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
