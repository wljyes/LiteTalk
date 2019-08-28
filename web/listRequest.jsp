<%@ page import="java.util.List" %>
<%@ page import="club.wljyes.bean.FriendRequest" %><%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2019/8/28
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>listRequest</title>
</head>
<jsp:include page="include/fore/foreHeader.jsp"></jsp:include>
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
            <th>同意</th>
            <th>拒绝</th>
            </thead>
            <tbody>
            <% for (FriendRequest fr : (List<FriendRequest>) request.getAttribute("requests")) { %>
            <tr>
                <td><%=fr.getFromUser()%></td>
                <td><%=fr.getFromUser()%></td>
                <td><a href="fore_friendRequest_update?isAgree=true&fromUser=<%=fr.getFromUser()%>">同意</a> </td>
                <td><a href="fore_friendRequest_update?isAgree=false&fromUser=<%=fr.getFromUser()%>">拒绝</a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>

    <%@include file="include/fore/forePage.jsp"%>
</body>
</html>
