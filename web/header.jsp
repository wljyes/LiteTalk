<%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/25
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>Hello Amaze UI</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <link rel="icon" type="image/png" href="assets/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <!--在这里编写你的代码-->

    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="assets/js/jquery.min.js"></script>
    <!--<![endif]-->
    <!--[if lte IE 8 ]>
    <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
    <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
    <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
    <![endif]-->
    <script src="assets/js/amazeui.min.js"></script>
</head>
<%--<header class="am-topbar">--%>
<%--    <div class="am-container">--%>
<%--        <h1 class="am-topbar-brand">--%>
<%--            <a href="#">Lite Talk</a>--%>
<%--        </h1>--%>
<%--        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"--%>
<%--                data-am-collapse="{target: '#doc-topbar-collapse'}">--%>
<%--            <span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>--%>
<%--        </button>--%>
<%--        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">--%>
<%--            <div class="am-topbar-right">--%>
<%--                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">注册</button>--%>
<%--            </div>--%>
<%--            <div class="am-topbar-right">--%>
<%--                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">登录</button>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</header>--%>
<header class="am-topbar">
    <div class="am-container">
        <h1 class="am-topbar-brand">
            <a href="#">Lite Talk</a>
        </h1>
        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
                data-am-collapse="{target: '#doc-topbar-collapse'}">
            <span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span>
        </button>
        <div class="am-collapse am-topbar-collapse" id="doc-topbar-collapse">
            <div class="am-topbar-right">
                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm">
                    <a href="register.jsp" style="color: white" id="register">注册</a>
                </button>
                <button class="am-btn am-btn-primary am-top-bar-btn am-btn-sm" id="logout" style="display: none">
                    <a href="logout" style="color: white">注销</a>
                </button>
            </div>
            <div class="am-topbar-right">
                <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm" id="login">
                    <a href="login.jsp" style="color: white">登录</a>
                </button>
                <a href="user/user.jsp" style="display: none" id="username-label"></a>
            </div>
        </div>
    </div>
</header>

<script>
    var isLogin = ${isLogin eq null ? false : isLogin};
    if (isLogin) {
        var username = ${user.username};
        $('#register').hide();
        $('#logout').show();
        $('#login').hide();
        var usernameLabel = $('#username-label');
        usernameLabel.show();
        usernameLabel.val(username);
    }
</script>

