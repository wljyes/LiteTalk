<%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/26
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
<%@include file="header.jsp"%>
<div class="am-container">
    <form class="am-form" action="register" method="post">
        <fieldset>
            <legend>注册</legend>

            <div class="am-form-group">
                <label for="doc-ipt-email-1">用户名</label>
                <input type="text" class="" id="doc-ipt-email-1" placeholder="输入用户名" name="username">
                <div style="display: none" id="username-error"></div>
            </div>

            <div class="am-form-group">
                <label for="doc-ipt-pwd-1">密码</label>
                <input type="password" class="" id="doc-ipt-pwd-1" placeholder="设置个密码吧" name="password">
                <div style="display: none" id="password-error"></div>
            </div>
            <p><button type="submit" class="am-btn am-btn-default" id="register">注册</button></p>
            <div style="display: none" id="register-error"></div>
        </fieldset>
    </form>
    <div class="am-container" id="info"></div>
</div>
</body>
<script src="js/logOrRegDeal.js"></script>
<script type="text/javascript">
    // $(function () {
    //    $('#doc-ipt-email-1').keyup(function () {
    //        var value = $(this).val();
    //        var page = "checkName.jsp";
    //         $.ajax({
    //             url: page,
    //             data: {"username": value},
    //             success: function (result) {
    //                 $('#info').html(result);
    //             }
    //         });
    //
    //        var t = $('#doc-ipt-email-1').val();
    //        if (!(t == null || t === "")) {
    //            $('#register').attr('disabled', false)
    //        } else {
    //            $('#register').attr('disabled', true)
    //        }
    //    })
    // });
    // $(function () {
    //     $('#doc-ipt-pwd-1').keyup(function () {
    //         var t = $('#doc-ipt-pwd-1').val();
    //         if (!(t == null || t === "")) {
    //             $('#register').attr('disabled', false);
    //         } else {
    //             $('#register').attr('disabled', true)
    //         }
    //     })
    // })
    loadFoam("doc-ipt-email-1", "doc-ipt-pwd-1", '${username}', '${password}');
    var usernameError = "${errorMap.usernameError}";
    var passwordError = "${errorMap.passwordError}";
    var registerError = "${errorMap.registerError}";
    var ue = $('#username-error');
    if (usernameError != '') {
        insertError(ue, usernameError);
    }
    var pe = $('#password-error');
    if (passwordError != '') {
        insertError(pe, passwordError);
    }
    var le = $('#register-error');
    if (registerError != '') {
        insertError(le, registerError);
    }
</script>
</html>
