<%--
  Created by IntelliJ IDEA.
  User: Well
  Date: 2019/5/23
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%><%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
  path + "/";
%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <title>Hello Amaze UI</title>

  <!-- Set render engine for 360 browser -->
  <meta name="renderer" content="webkit">

  <!-- No Baidu Siteapp-->
  <meta http-equiv="Cache-Control" content="no-siteapp"/>

  <link rel="icon" type="image/png" href="assets/i/favicon.png">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
  <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">

  <!-- Tile icon for Win8 (144x144 + tile color) -->
  <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
  <meta name="msapplication-TileColor" content="#0e90d2">

  <link rel="stylesheet" href="assets/css/amazeui.min.css">
  <link rel="stylesheet" href="assets/css/app.css">
  <!--在这里编写你的代码-->

  <!--[if (gte IE 9)|!(IE)]><!-->
  <script src="assets/js/jquery.min.js"></script>
  <!--<![endif]-->
  <!--[if lte IE 8 ]>
  <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
  <script src="assets/js/amazeui.ie8polyfill.min.js"></script>
  <![endif]-->
  <script src="assets/js/amazeui.min.js"></script>
</head>
<body>
  <jsp:include page="header.jsp"/>
  <script>
    var isLogin = ${isLogin eq null ? false : isLogin};
    if (isLogin) {
      var username = '${user.username}';
      $('#register').hide();
      $('#logout').show();
      $('#login').hide();
      var usernameLabel = $('#username-label');
      $('#username-href').html(username);
      usernameLabel.show();
    }
  </script>
  <div id="main" style="margin-top: 10px">
    <!-- 聊天内容展示区域 -->
    <div id="ChatBox" class="am-g am-g-fixed">
      <div class="am-u-sm-12" style="height: 400px;border:1px solid #999;overflow-y: scroll;">
        <u id="chatContent" class="am-comments-list am-comments-list-flip">
          <li id="cmt" class="am-comment" style="display: none">
            <a href="">
              <img class="am-comment-avatar" src="assets/i/app-icon72x72@2x.png" alt="头像">
            </a>
            <div class="am-comment-main">
              <header class="am-comment-hd">
                <a href="#link_to_user" class="am-comment-author" f="nickname">昵称</a>&nbsp;
                <time datetime="" title="" f="time">2019-5-23 21:30</time>
              </header>
              <div class="am-comment-bd" f="content">消息内容</div>
            </div>
          </li>
          <li class="am-comment am-comment-warning am-comment-flip" style="margin-left: 20%;display: none">
            <a href="">
              <img class="am-comment-avatar" src="assets/i/app-icon72x72@2x.png" alt="头像">
            </a>
            <div class="am-comment-main">
              <header class="am-comment-hd">
                <a href="#link_to_user" class="am-comment-author">昵称</a>&nbsp;
                <time datetime="" title="">2019-5-23 21:30</time>
              </header>
              <div class="am-comment-bd">消息内容</div>
            </div>
          </li>
        </u>
      </div>
    </div>
    <!-- 消息编辑区域 -->
    <div id="editBox" class="am-g am-g-fixed">
      <script type="text/plain" id="myEditor" style="width:100%;height:140px;"></script>
      <button id="send" type="button" class="am-btn am-btn-primary am-btn-block">发送</button>
    </div>
  </div>

  <link href="umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
  <script type="text/javascript" src="umeditor/umeditor.config.js"></script>
  <script type="text/javascript" src="umeditor/umeditor.js"></script>
  <script type="text/javascript" src="umeditor/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">$(function(){
    //窗口大小变化时，调整显示效果
    $("#ChatBox div:eq(0)").height($(this).height()-290);
    $(window).resize(function(){
      $("#ChatBox div:eq(0)").height($(this).height()-290);
    });
    //实例化编辑器
    var um = UM.getEditor('myEditor',{
      initialContent:"请输入聊天信息...",
      autoHeightEnabled:false,
      toolbar:[
        'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
        'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
        '| justifyleft justifycenter justifyright justifyjustify |',
        'link unlink | emotion image video  | map'
      ]
    });
<%--    <%session.setAttribute("username", );%>--%>
    var isLogin = ${isLogin eq null ? false : isLogin};
    var username;
    var nickname;
    if (isLogin) {
      nickname = '${user.nickname eq null ? user.username : user.nickname}';
      username = '${user.username}';
    } else {
      window.location.href="login.jsp";
    }
    var ws = new WebSocket("ws://localhost:80/LiteTalk/websocket/" + "${user.username}");
    ws.onmessage = function (ev) {
      var obj = eval('(' + ev.data + ')');
      addMessage(obj);
    };

    window.onbeforeunload = function () { ws.close() };
    $('#send').click(function () {
      if (!um.hasContents()) {
        um.focus();
        $('.edui-container').addClass('am-animation-shake');
        setTimeout($('.edui-container').removeClass('am-animation-shake'), 1000);
      } else {
        var text = um.getContent();
        var str = {"username":username, "nickname":nickname, "content":text};
        var obj = JSON.stringify(str);
        ws.send(obj);
        um.setContent('');
        um.focus();
      }
    });
  });
  </script>
  </body>
<script type="text/javascript">
  function addMessage(msg) {
    var cmt = $('#cmt').clone();
    cmt.show();
    cmt.appendTo("#chatContent");
    cmt.find('[f=nickname]').html(msg.nickname);
    cmt.find('[f=time]').html(msg.time);
    cmt.find('[f=content]').html(msg.content);
    cmt.addClass(msg.isSelf ? 'am-comment-flip' : '');
    cmt.addClass(msg.isSelf ? 'margin-left:20%' : 'margin-right:20%');
    $('#ChatBox div:eq(0)').scrollTop(99999);
  }
</script>
</html>
