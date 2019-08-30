<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2019/8/27
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>listFriend</title>
</head>
<jsp:include page="include/fore/foreHeader.jsp"/>
<script>
    checkLogin();
</script>
<body>
<div class="am-container">
    <div class="am-list-main">
        <table id="friendList" class="am-table am-table-bordered" style="align-content: center">
            <thead>
                <th>用户名</th>
                <th>昵称</th>
                <th>状态</th>
                <th>聊天</th>
                <th>删除</th>
            </thead>
            <tbody>
                <% for (String toUser : (List<String>) request.getAttribute("friends")) { %>
                <tr>
                    <td><%=toUser%></td>
                    <td><%=toUser%></td>
                    <td>null</td>
                    <td><a href="user/conversation.html?toUser=<%=toUser%>">聊天</a></td>
                    <td><a href="fore_friend_delete?toUser=<%=toUser%>">删除</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <%@include file="include/fore/forePage.jsp"%>
</div>
</body>
</html>
