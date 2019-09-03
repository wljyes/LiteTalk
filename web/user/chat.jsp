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
    var toUser = '${requestScope.toUser}';
    var id = document.cookie;
    var jsessionid = getSessionId();
    ws = new WebSocket("ws://localhost:8080/LiteTalk/websocket/" + jsessionid);
    ws.onmessage = function (ev) { var msg = eval('(' + ev.data + ')'); showMsg('chat-list', msg, false); };
    window.onbeforeunload = function () { ws.close(); };
    $('#message_send').click(function () {
        if (isEmpty('message_input')) {
            $('#message_input').focus();
            return;
        }
        var msg = {"from_user":'${user.username}', "to_user":toUser, "content":$('#message_input').val()};
        sendMsg(msg);
        showMsg('chat-list', msg, true);
    });
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
