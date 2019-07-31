<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"></jsp:include>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"></jsp:include>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">添加歌手</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal col-md-6 col-md-offset-3" role="form" action="singer.action" enctype="multipart/form-data" id="addfrom" method="post">
                        <div class="form-group">
                            <label for="singerName" class="col-sm-4 control-label">歌手名字</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="singerName" name="singerName" placeholder="请输入名字">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="singerImgFile" class="col-sm-4 control-label">歌手图片</label>
                            <div class="col-sm-8">
                                <input type="file" class="form-control" id="singerImgFile" name="singerImgFile" placeholder="请选择图片">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="submit">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $("#nav li:nth-child(1)").addClass("active")
</script>
</html>