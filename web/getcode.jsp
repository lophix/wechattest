<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/9/2
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.util.*,java.net.URLEncoder" %>
<%
    String url = "https://open.weixin.qq.com/connect/oauth2/authorize";
//    String grant_type = "refresh_token";

    String beforeUrl = "http://weixin.win-sky.com.cn/testwechat/index.jsp";
    String redirect_uri = URLEncoder.encode(beforeUrl,"utf-8");
    String params = "";
    params = "appid=" + CommonDataUtil.APP_ID + "&redirect_uri=" + redirect_uri +"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
    String realUrl = url + "?" + params;
    System.out.println("come in");
    response.sendRedirect(realUrl);
%>
