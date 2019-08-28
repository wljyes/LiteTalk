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
    <script src="http://how2j.cn/study/js/jquery/2.0.0/jquery.min.js"></script>
    <link href="http://how2j.cn/study/css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="http://how2j.cn/study/js/bootstrap/3.3.6/bootstrap.min.js"></script>
</head
<jsp:include page="foreHeader.jsp"/>
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
                    <td><a href="user/conversation.html?toUser=<%=toUser%>>">聊天</a></td>
                    <td><a href="fore_friend_delete?toUser=<%=toUser%>">删除</a></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

    <script>
        $(function () {
            $("ul.am-pagination li.disabled a").click(function () {
                return false;
            })
        });
    </script>

    <div class="am-center">
        <nav>
            <ul data-am-widget="pagination" class="am-pagination am-pagination-default">
                <li class="am-pagination-first ${page.hasPrevious ? "" : "disabled"}>
                    <a href="?page.start=0">第一页</a>
                </li>
                <li class="am-pagination-prev ${page.hasPrevious ? "" : "disabled"}>
                    <a href="?page.start=${page.start - page.count}">上一页</a>
                </li>
                <li class="am-pagination-next ${page.hasNext ? "" : "disabled"}>
                    <a href="?page.start=${page.start - page.count}">下一页</a>
                </li>
                <li class="am-pagination-last ${page.hasNext ? "" : "disabled"}>
                    <a href="?page.start=${page.last}">最末页</a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
