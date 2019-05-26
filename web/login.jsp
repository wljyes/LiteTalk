<%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/25
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="am-container">
    <form class="am-form" action="login" method="post">
        <fieldset>
            <legend>登录</legend>

            <div class="am-form-group">
                <label for="doc-ipt-email-1">用户名</label>
                <input type="text" class="" id="doc-ipt-email-1" placeholder="输入用户名" name="username">
            </div>

            <div class="am-form-group">
                <label for="doc-ipt-pwd-1">密码</label>
                <input type="password" class="" id="doc-ipt-pwd-1" placeholder="输入密码" name="password">
            </div>
            <p><button type="submit" class="am-btn am-btn-default">登录</button></p>
        </fieldset>
    </form>
    <div class="am-container" id="info"></div>
</div>
</body>
<script>
    var code = ${code eq null ? 0 : code};
    if (code === 500) {
        $('#info').html("登录失败");
    }
</script>
</html>
