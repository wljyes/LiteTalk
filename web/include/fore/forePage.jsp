<%--
  Created by IntelliJ IDEA.
  User: whh
  Date: 2019/8/28
  Time: 20:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <li class="am-pagination-first ${page.hasPrevious ? "" : "disabled"}">
                    <a href="?page.start=0">第一页</a>
            </li>
            <li class="am-pagination-prev ${page.hasPrevious ? "" : "disabled"}">
                    <a href="?page.start=${page.start - page.count}">上一页</a>
                </li>
                <li class="am-pagination-next ${page.hasNext ? "" : "disabled"}">
                <a href="?page.start=${page.start + page.count}">下一页</a>
            </li>
            <li class="am-pagination-last ${page.hasNext ? "" : "disabled"}">
                    <a href="?page.start=${page.last}">最末页</a>
                </li>
            </ul>
        </nav>
    </div>
