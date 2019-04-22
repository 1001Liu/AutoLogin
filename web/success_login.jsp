<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/20 0020
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<c:if test="${not empty user}">
    <h1>欢迎${user.username}登录</h1><br>
</c:if>

<c:if test="${ empty user}">
    <h1>用户或者密码错误!!!!</h1>
    <%@include file="index.jsp"%>
</c:if>

</body>
</html>
