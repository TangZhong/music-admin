<%--
  Created by IntelliJ IDEA.
  User: 86186
  Date: 2019/7/29
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <div class="row"  style="margin-top: 280px; ">
        <div class="col-md-4"></div>

        <div class="col-md-4" id="login-box">

            <form class="form-horizontal" role="form" action="login" id="from1" method="post">

                <div class="form-group">

                    <label class="text-warning">${msg}</label>

                </div>

                <div class="form-group">

                    <label for="account" class="col-sm-3 control-label">用户id</label>

                    <div class="col-sm-9">

                        <input type="text" class="form-control" id="account" placeholder="请输入名字" name="account">

                    </div>

                </div>

                <div class="form-group">

                    <label for="password" class="col-sm-3 control-label">密码</label>

                    <div class="col-sm-9">

                        <input type="password" class="form-control" id="password" placeholder="请输入密码" name="password">

                    </div>

                </div>


                <div class="form-group pull-right">

                    <div class="col-sm-offset-2 col-sm-10">

                        <button type="submit" class="btn btn-default btn-info">登录</button>

                    </div>

                </div>

            </form>

        </div>

        <div class="col-md-4"></div>

    </div>
    </div>

</div>
</body>
</html>
