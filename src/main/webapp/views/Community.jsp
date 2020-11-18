<%@ page language="java" import="java.util.*" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="../statics/js/Exercise.js"></script>
    <script src="../statics/js/Community.js"></script>
    <script src="../statics/js/Index.js"></script>
    <title>Health Heaven</title>
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

        #postTable {
            border-collapse: collapse;
            margin-left: auto;
            margin-right: auto;
            font-size: 0.9em;
            font-family: sans-serif;
            width: 80%;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
        }

        #postTable th, #postTable td{
            padding: 12px 15px;
        }

        #postTable tr {
            border-bottom: 1px solid #dddddd;
        }

        #postTable tr:nth-of-type(even) {
            background-color: #f3f3f3;
        }

        #postTable tr:last-of-type {
            border-bottom: 2px solid #009879;
        }

    </style>
</head>
<script>
    function mysetting() {
        console.log("go to setting");
        window.open("/setting");
        return false;

    }


</script>

<body onload="updatePosts();">
<%--login & logout--%>
<section class="top-bar-area">
    <div id="unlogin" style="display: ${requestScope.formlogin}">
        <form action="/login" method="post">
            <div style="padding-bottom: 0">
                <div class="col-xs-4 form-inline row" style=" margin-left:15%;">
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
        <h1>COMMUNITY</h1>
    </div>
</section>
<div class="container">

    <div style="text-align: right; margin:5px">
        <button type="button" class="btn btn-primary" onclick="updatePosts()">Refresh</button>
    </div>

    <div id="publishButton" style="display:${requestScope.publish}">
        <div style="text-align: right">
        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#publish">Publish post
        </button>
        </div>
    </div>

    <hr>

    <table id="postTable">
    </table>

</div>


</nav>
<%--发布模态框--%>
<div class="modal left fade" id="publish" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Publish New</h5>
            </div>
            <form action="/publishPosts" method="post" onsubmit="return false" id="publishform">
                <div class="modal-body">
                    <input name="Title" placeholder="title" class="col-xs-1 form-control input-group-sm" type="text">
                    <p></p>

                    <span>Sports </span><input type="radio" name="Tag" value="Sports">
                    <span>Food </span><input type="radio" name="Tag" value="Food">
                    <span>Relationship </span><input type="radio" name="Tag" value="Relationship">
                    <span>Lifestyle </span><input type="radio" name="Tag" value="Lifestyle">
                    <span>Emotion </span><input type="radio" name="Tag" value="Emotion">
                    <span>Other </span><input type="radio" name="Tag" value="Other">
                    <p></p>

                    <textarea name="Content" placeholder="content" class="col-xs-1 form-control input-group-sm"
                              type="text"></textarea>
                    <p></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-warning" data-dismiss="modal">close</button>
                    <button type="submit" class="btn btn-info" onclick="publishPost()">Publish</button>
                </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<%--content模态框--%>
<div class="modal left fade" id="content" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="currentContent">Content</h5>
            </div>
            <div class="modal-body">
                <div class="postTag"></div>
                <div class="showContent"></div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal">close</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>

<style>
    .nav-item {
        font-size: 25px;
    }
</style>