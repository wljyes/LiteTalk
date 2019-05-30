<%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/29
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <script src="../js/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<script>
    var isLogin = ${isLogin eq null ? false : isLogin};
    if (isLogin) {
        var username = '${user.username}';
        $('#register').hide();
        $('#logout').show();
        $('#login').hide();
        var usernameLabel = $('#username-label');
        $('#username-href').html(username);
        usernameLabel.show();
    }
</script>
username: <span>${sessionScope.user.username}</span> <br>
nickname:<input type="text" id="nickname" value="${sessionScope.user.nickname}">
<button id="change">修改</button> <span id="info"></span> <br>
</body>
</html>
<script>
    $(function () {
        $('#change').click(function () {
            var value = $('#nickname').val();
            var data = {"nickname":value, "username":'${user.username}'};
            var url = "/LiteTalk/changeNickname";
            $('#info').load(url, data);
        })
    })
</script>
