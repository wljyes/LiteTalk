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
</head>
<body>
username: <span>${sessionScope.user.username}</span> <br>
nickname:<input type="text" id="nickname" value="${sessionScope.user.nickname}">
<button id="change">修改</button> <span id="info"></span> <br>
</body>
</html>
<script>
    $(function () {
        $('#change').click(function () {
            var value = $('#nickname').val();
            var data = {"nickname":value, "user":${user}};
            var url = "/changeNickname";
            $('#info').load(url, data);
        })
    })
</script>
