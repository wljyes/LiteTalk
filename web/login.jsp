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
                <div style="display: none" id="username-error"></div>
            </div>

            <div class="am-form-group">
                <label for="doc-ipt-pwd-1">密码</label>
                <input type="password" class="" id="doc-ipt-pwd-1" placeholder="输入密码" name="password">
                <div style="display: none" id="password-error"></div>
            </div>
            <p>
                <button type="submit" class="am-btn am-btn-default" id="login">登录</button>
            </p>
            <div style="display: none" id="login-error"></div>
        </fieldset>
    </form>
    <div class="am-container" id="info"></div>
</div>
</body>
<script src="js/logOrRegDeal.js"></script>
<script type="text/javascript">
     var usernameError = "${usernameError}";
     var passwordError = "${passwordError}";
     var loginError = "${error}";
     loadFoam("doc-ipt-email-1", "doc-ipt-pwd-1", '${username}', '${password}');
     var ue = $('#username-error');
     if (usernameError != '') {
         insertError(ue, usernameError);
     }
     var pe = $('#password-error');
     if (passwordError != '') {
         insertError(pe, passwordError);
     }
     var le = $('#login-error');
     if (loginError != '') {
         insertError(le, loginError);
     }
</script>
</html>
