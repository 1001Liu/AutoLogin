<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <style>
    #div1{
      width: 100px;
      height: 50px;
      float: left;
    }
    #div2{
      float: left;
    }
  </style>
  <body>
    <form id="from1" action="LoginServlet?op=1" method="post">
      用户:<input type="text" name="username" id="username"><br><br>
      密码:<input type="password" name="password" id="password"><br><br>
      <div id="div2">
        验证码:<input type="text" name="verification" id="verification" onblur="graphic()" >
        <span id="span1" style="size: 3px;color: red"></span>
      </div>
      <div id="div1">
        <img src="GraphicServlet" onclick="this.src=this.src+'?'+Math.random()" id="imag" style="width: 100%">
      </div>
      <br><br><br><br>
      <input type="checkbox" name="repassword" id="repassword" onclick="remember()">记住密码
      <input type="checkbox" name="autoLogin" id="autoLogin">自动登录<br><br>
      <input type="submit" value="登录" ><br><br>
    </form>
    <script src="js/jquery.js"></script>
    <script src="js/auto_login.js"></script>
    <script type="text/javascript" src="js/jquery.validate.js"></script>
    <script type="text/javascript" src="js/messages_zh.js"></script>
    <script type="text/javascript" src="js/jquery.cookie1.js"></script>
  <script>
    function graphic() {
      var str = $.cookie('str');
      var str1 = $('#verification').val();
      if (str.toLowerCase() != str1.toLowerCase()) {
        $('#span1').text('验证码输入有误')
        $('#imag').attr('src','GraphicServlet?aaa='+Math.random())
      }else {
        $('#span1').text('')
      }
    }
    //获取焦点的时候发送ajax把里面的值重新存
    /*$(function () {
      $('#verification').focus(function () {
        $.ajax({
          url:'GraphicServlet',
          type:'post',
          success:function () {

          }
        })
      })
    })*/

  </script>
  </body>
</html>
