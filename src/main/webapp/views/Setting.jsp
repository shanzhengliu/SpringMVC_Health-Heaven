<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/10/29
  Time: 9:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8"  isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="../statics/js/Exercise.js"></script>
    <script src="../statics/js/Index.js"></script>
    <style type="text/css">
        <%--Login/sign up--%>
        .top-bar-area {
            position: relative;
            background: #FFFFFF;
            display: block;
            border-bottom: 1px solid #155724;
            padding-left: 750px;
            padding-top: 14px;
        }

        .top-bar-area img {
            width: 20px;
            height: 20px;
        }

        .top-bar-area a {
            background-color: transparent;
            border: 1px solid black;
        }

        .top-bar-area input {
            border: 1px solid black;
            background-color: transparent;
        }

        .top-bar-area button {
            border: 1px solid black;
            background-color: darkgrey;

        }

        <%--menu--%>
        .navcontainer {
            list-style: none;
            background-color: #0278ae;
            color: white;
            position: relative;
            margin-top: 20px;
            margin: auto;
        }

        .navcontainer a {
            font-family: "Microsoft YaHei UI";
            color: white;
            text-decoration: none;
        }

        .navcontainer li:hover {
            background-color: orange;
        }

        .navcontainer a:hover {
            color: #fff;
        }

        .navcontainer ul {
            border-bottom: transparent;
        }

        .row {
            text-align: center;
        }

        .nav {
            position: relative;
        }

        .nav-item {
            font-size: 25px;
        }

        .main {
            background-color: #51adcf;
            color: white;
            margin-top: -2%;
            height: 7em;
        }

        .main h1 {
            text-align: center;
            padding-top: 45px;
        }
    </style>
    <title>Health Heaven</title>
</head>
<body>
<%--login & logout--%>
<section class="top-bar-area">
    <div id="unlogin" style="display: ${requestScope.formlogin}">
        <form action="/login" method="post">
            <div style="padding-bottom: 0">
                <div class="col-xs-4 form-inline row" style="margin-left:15%;">
                    <input name="Email" placeholder="Email" class="col-xs-1 form-control input-sm" id="email"
                           type="email">&nbsp; &nbsp;
                    <input name="Password" placeholder="Password" class="col-xs-1 form-control input-sm" id="password"
                           type="password">&nbsp; &nbsp;
                    <button class="btn btn btn-info" type="submit">Login</button>&nbsp;
                    <a class="btn btn btn-info"
                       href="https://github.com/login/oauth/authorize?client_id=Iv1.31d2086604f72df9&state=javaboy"><img
                            src="../statics/image/github.png"></a>&nbsp;
                    <button class="btn btn btn-info" type="button" data-toggle="modal" data-target="#signup">Sign Up
                    </button>&nbsp; &nbsp;
                </div>
            </div>
        </form>
    </div>
    <div id="havelogin" style="display:${requestScope.formsetting}">
        <div style="padding-bottom: 0">
            <form action="/logout" method="get">
                <div class="col-xs-4 form-inline row" style="margin-left:68%;">
                    <span>hello,${requestScope.username}</span> &nbsp; &nbsp;
                    <button class="btn btn btn-info" type="button" onclick="mysetting()">Setting</button> &nbsp; &nbsp;
                    <button class="btn btn btn-info" type="submit">Logout</button> &nbsp; &nbsp;
                </div>
            </form>
        </div>
    </div>
</section>
<%--menu--%>
<div class="navcontainer" id="navbarNav">
    <div class="span6">
        <ul class="nav nav-tabs">
            <li class="nav-item active">
                <a class="nav-link"
                   style="padding-left: 32px;"
                   href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/community">Community</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/food-tracker">Food</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/exercise-tracker">Exercise</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/product/showAll">Products</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/report/showCharts">Report</a>
            </li>
        </ul>
    </div>
</div>
<section>
    <div class="main">
        <h1>SETTING</h1>
    </div>
</section>
<div style="text-align: center" >
    <form action="/update" method="post">
<%--<span>UserName:</span>    <input name="username"  value="${requestScope.username}" placeholder="${requestScope.username}"><p></p>--%>
<span>NewPassword:</span>    <input name="newpassword" placeholder="New PassWord"><p></p>


    <button type="submit">Update</button>

    </form>
</div>
</body>
</html>

<style>


    .nav-item {
        font-size: 25px;
    }



</style>
