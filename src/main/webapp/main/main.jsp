<%@page contentType="text/html; UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="app"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="../login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../login/assets/js/jquery-1.11.1.min.js"></script>
    <script src="../login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <script src="../kindeditor/kindeditor-all-min.js"></script>
    <script src="../kindeditor/lang/zh-CN.js"></script>
    <script src="../echart/echarts.min.js"></script>
    <script src="../echart/china.js"></script>
    <script>
        $(function () {
            $("#myCarousel").carousel({
                interval:2500,  //毫秒
            })
        })
        function userAdd() {
            $(".modal").modal('show');
            var select = $("#province");
            $.ajax({
                url:"${pageContext.request.contextPath}/user/selectAll",
                type:"post",
                datatype:"JSON",
                success:function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var option = $("<option value='"+data[i].code+"'></option>").text(data[i].name);
                        select.append(option);
                        select.trigger('change');  //页面加载 使下拉框自动刷新一次
                    }
                }
            });
        }
        function addCity(id) {
            var city = $("#city").empty();
            $.ajax({
                url:"${pageContext.request.contextPath}/user/selectCity",
                type:"post",
                datatype:"JSON",
                data:"id="+$(id).find(':selected').prop("value"),
                success:function (data) {
                    for (var i = 0; i < data.length; i++) {
                        var option = $("<option value='"+data[i].code+"'></option>").text(data[i].name);
                        city.append(option);
                    }
                }
            });
        }
        function mouseup() {
            $("#modal").modal("hide");
            alert("注册成功")
        }
    </script>
    <style>
        #tableDataSearch tr td{
            text-align:center;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持名法洲后台管理系统</a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> <span style="color: orangered">${username}</span></a></li>
                <li class="dropdown">
                    <a href="${app}/admin/out">安全退出 <span class="glyphicon glyphicon-remove-circle"></span></a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<div class="container-fluid">
    <div class="row">
        <!--左侧手风琴-->
        <div class="col-sm-2">
            <div class="panel-group" id="accordion">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./echart.jsp')">全国分布图</a><br><br>
                            <a href="javascript:$('#lay-right').load('./line.jsp')">折线统计图</a><br><br>
                            <a data-toggle="modal" data-target="modal" onclick="userAdd()" href="">用户注册</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./users.jsp')">上师列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./article.jsp')">文章列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFour">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./album.jsp')">专辑列表</a>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <a href="javascript:$('#lay-right').load('./banner.jsp')">轮播图列表</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10" id="lay-right">
            <div class="well well-lg" >
                <h3>欢迎来到持法名洲后台管理系统</h3>
            </div>
            <!--右侧默认轮播图-->
            <div id="myCarousel" class="carousel slide">
                <!-- 轮播（Carousel）指标 -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>
                <!-- 轮播（Carousel）项目 -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${app}/img/shouye.jpg" alt="First slide">
                    </div>
                    <div class="item">
                        <img src="${app}/img/shouye.jpg" alt="Second slide">
                    </div>
                    <div class="item">
                        <img src="${app}/img/shouye.jpg" alt="Third slide">
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="well well-sm text-center navbar-fixed-bottom">&copy;百知教育 baizhi@zparkhr.com.cn</div>
<!--用户注册模态框-->
<div class="modal fade" id="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">用户注册</h4>
            </div>
            <div class="modal-body">
                <!--提交的表单-->
                <form action="${pageContext.request.contextPath}/user/addUser" method="post" class="form-horizontal">
                    <!--法号-->
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">dharma</label>
                        <div class="col-sm-10">
                            <input type="text" name="dharma" class="form-control" id="inputEmail3" placeholder="法号">
                        </div>
                    </div>
                    <!--真实姓名-->
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">真实姓名</label>
                        <div class="col-sm-10">
                            <input type="text" name="name" class="form-control" id="inputPassword3" placeholder="真实姓名">
                        </div>
                    </div>
                    <!--省份-->
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">省</label>
                        <div class="col-sm-3">
                            <select class="form-control" onchange='addCity(this)' id="province" name="province">
                            </select>
                        </div>
                        <label for="inputPassword3" class="col-sm-2 control-label">市</label>
                        <div class="col-sm-3">
                            <select class="form-control" id="city" name="city">
                            </select>
                        </div>
                    </div>
                    <!--用户名-->
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">username</label>
                        <div class="col-sm-10">
                            <input type="text" name="username" class="form-control" id="inputPassword6" placeholder="用户名">
                        </div>
                    </div>
                    <!--密码-->
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">password</label>
                        <div class="col-sm-10">
                            <input type="password" name="password" class="form-control" id="inputPassword7" placeholder="密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" onmousedown="mouseup()" class="btn btn-success">提交</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

</body>
</html>