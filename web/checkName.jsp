<%@ page import="club.wljyes.bean.User" %>
<%@ page import="club.wljyes.dao.UserDAOImp" %><%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/26
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String username = request.getParameter("username");
    if (new UserDAOImp().getByName(username) == null) {
        out.print("<font color='green'>用户名可用</font>");
    } else {
        out.print("<font color='red'>用户名已存在</font>");
    }
%>
