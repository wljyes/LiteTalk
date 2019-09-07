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
    <script src="js/jquery.min.js"></script>
    <script src="js/logOrRegDeal.js"></script>
</head>
<body>
<%@include file="include/fore/foreHeader.jsp"%>
<script>
    checkLogin();
    // function showMsg(msg) {
    //     var from_user = msg.fromUser;
    //     var content = msg.content;
    //     var date = msg.date;
    //     document.getElementById('conversation').innerHTML =
    //         "<span style='color:red'>" +
    //         from_user + '<br>' +
    //         content + '<br>' +
    //         date +
    //         "</span>" + '<br>';
    // }
    <%--ws = new WebSocket("ws://localhost:8080/LiteTalk/websocket/" + "${user.username}");--%>
    <%--ws.onmessage = function (ev) { var msg = eval('(' + ev.data + ')'); showMsg(msg); };--%>
    <%--window.onbeforeunload = function () { ws.close(); };--%>

    $(function () {
        $('#addFriend').click(function () {
            if (isEmpty('username')) {
                insertError($('#addMsg'), "用户名不能为空！");
            } else {
                var toUser = $('#username').val();
                $('#addMsg').load("fore_friend_add", {"toUser":toUser});
            }
        })
    })
</script>
<%--发送给：<input type="text" id="to_user"> 内容：<input type="text" id="content"> <button id="send">发送</button> <br>--%>

用户名：<input type="text" id="username"> <button id="addFriend">添加</button> <span id="addMsg"></span> <br>

<button type="button"><a href="fore_friend_list">好友列表</a></button>

<button type="button"><a href="fore_friendRequest_list">请求列表</a></button>

<div id="conversation"></div>
</body>
</html>
<%--<script>--%>
<%--        $('#send').click(function () {--%>
<%--            var username = "${user.username}";--%>
<%--            var to_user = document.getElementById('to_user').value;--%>
<%--            var content = document.getElementById('content').value;--%>
<%--            var msg = {"from_user":username, "to_user":to_user, "content":content};--%>
<%--            var url = "${pageContext.request.contextPath}/sendMessage";--%>
<%--            var xmlHttp = new XMLHttpRequest();--%>
<%--            xmlHttp.open("POST", url, true);--%>
<%--            xmlHttp.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');--%>
<%--            // var formData = new FormData();--%>
<%--            // formData.append("message", JSON.stringify(msg));--%>
<%--            xmlHttp.send("message=" + JSON.stringify(msg));--%>
<%--            document.getElementById('content').value = "";--%>
<%--        })--%>
<%--</script>--%>
