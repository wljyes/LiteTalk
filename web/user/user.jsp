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
<button id="changeNickname">修改</button> <span id="info"></span> <br>
修改密码：<br>
旧密码：<input type="text" id="oldPassword"> <br>
新密码：<input type="text" id="newPassword"> <br>
<button id="changePassword">修改密码</button> <span id="changePasswordInfo"></span>
</body>
</html>
<script>
    $(function () {
        $('#changeNickname').click(function () {
            var value = $('#nickname').val();
            var data = {"nickname":value};
            var url = "${pageContext.request.contextPath}/changeNickname";
            $('#info').load(url, data);
        });
        $('#changePassword').click(function () {
            var oldPassword = $('#oldPassword').val();
            var newPassword = $('#newPassword').val();
            //todo 不要明文传输密码/不要存密码
            var password = '${sessionScope.user.password}';
            if (oldPassword === password) {
                var url = "${pageContext.request.contextPath}/changePassword";
                $('#changePasswordInfo').load(url, {"password":newPassword});
            } else {
                $('#changePasswordInfo').html("旧密码错误");
            }
        })
    })
</script>
