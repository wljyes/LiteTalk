<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2019/9/2
  Time: 12:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>chat</title>
    <link rel="stylesheet" href="../css/chat/chat.css">
</head>
<%@include file="../include/fore/foreHeader.jsp"%>
<script src="../js/chat/chatUtil.js"></script>
<script>
    checkLogin();
    var index = window.location.href.search("toUser=");
    var toUser = window.location.href.substring(index + 7);
    var id = document.cookie;
    var jsessionid = getToken();
    ws = new WebSocket("ws://localhost:8080/LiteTalk/websocket/" + jsessionid);
    ws.onmessage = function (ev) { var msg = eval('(' + ev.data + ')'); showMsg('chat-list', msg, false); };
    window.onbeforeunload = function () { ws.close(); };
    $(function () {
        $('#message_send').click(function () {
            if (isEmpty('message_input')) {
                $('#message_input').focus();
                return;
            }
            var msg = {"from_user":'${user.username}', "to_user":toUser, "content":$('#message_input').val()};
            sendMsg(msg);
            showMsg('chat-list', msg, true);
        });
    });

    function sendMsg(msg) {
            var url = "${pageContext.request.contextPath}/sendMessage";
            var xmlHttp = new XMLHttpRequest();
            xmlHttp.open("POST", url, true);
            xmlHttp.setRequestHeader("Content-Type", 'application/x-www-form-urlencoded');
            // var formData = new FormData();
            // formData.append("message", JSON.stringify(msg));
            xmlHttp.send("message=" + JSON.stringify(msg));
            document.getElementById('message_input').value = "";
    }
</script>
<body>
<div class="chat-thread" style="height: 500px;margin-left: 200px;margin-right: 200px;margin-top: 20px;border: black">
    <ul id="chat-list">
    </ul>
</div>
<div class="input-group" style="margin-right: 200px; margin-left: 200px;margin-top: 10px">
    <input type="text" class="form-control" placeholder="Chat something..." id="message_input">
    <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="message_send">
            <span class="glyphicon glyphicon-send" aria-hidden="true"></span>
        </button>
    </span>
</div>
</body>
</html>
