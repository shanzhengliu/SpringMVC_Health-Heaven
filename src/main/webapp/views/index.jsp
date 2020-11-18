<%@ page language="java" import="java.util.*" isELIgnored = "false" pageEncoding="UTF-8"%>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
<script>
    function mysetting() {
        console.log("go to setting");
        window.open("/setting");
        return false;

    }

</script>

<body>
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
                       href="${pageContext.request.contextPath}">Home</a>
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
        <h1>HOME</h1>
    </div>
</section>


<div id="carouselExampleInterval" class="carousel slide row" data-ride="carousel" style="height: 22rem">
    <div class="carousel-inner">
        <div class="carousel-item active" data-interval="10000">
            <img src="../statics/banner/banner3.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item" data-interval="2000">
            <img src="../statics/banner/banner2.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
            <img src="../statics/banner/banner1.jpg" class="d-block w-100" alt="...">
        </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleInterval" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleInterval" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container-fluid content-row">
<div class="card-columns" style="padding-top: 3rem ">


    <div class="card col-sm shadow p-3 mb-4 bg-white rounded h-75" style="border: 0 ">

        <img class="card-img-top h-50" src="../statics/image/index_card1.jpg" alt="Card image">

        <div class="card-body" style="height: 40%">
            <h5 class="card-title"><span style="font-family: 'Droid Sans'">Healthy Life</span></h5>
            <p class="card-text"><span style="font-family: 'Droid Sans'">To Keep your healthy life, various of Information including sport, diet, will be provide int this project. You can easily found anything you expected.</span></p>

         </div>

        <a href="#" class="btn btn-primary footer"  style="float: right">See More</a>


    </div>
    <div class="card col-sm shadow p-3 mb-4 bg-white rounded h-75" style="border: 0">
        <img class="card-img-top h-50" src="../statics/image/index_card2.jpg" alt="Card image">
        <div class="card-body" style="height: 40%">
            <h5 class="card-title"><span style="font-family: 'Droid Sans'">Healthy Diet</span></h5>
            <p class="card-text">To Keep your healthy Diet, various of Information including sport, diet, will be provide int this project. You can easily found anything you expected.</p>
        </div>
        <a href="#" class="btn btn-primary footer"  style="float: right">See More</a>
    </div>
    <div class="card col-sm shadow p-3 mb-4 bg-white rounded h-75" style="border: 0">
        <img class="card-img-top h-50" src="../statics/image/index_card3.jpg" alt="Card image">

        <div class="card-body"  style="height: 40%">
            <h5 class="card-title"><span style="font-family: 'Droid Sans'">Healthy Relationship</span></h5>
            <p class="card-text">To Keep your healthy Relationship, various of Information including sport, diet, will be provide int this project. You can easily found anything you expected.</p>
        </div>
        <a href="#" class="btn btn-primary footer"  style="float: right">See More</a>
    </div>

</div>
</div>

<%--注册模态框--%>
<div class="modal left fade" id="signup" tabindex="-1" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="myModalLabel">Sign up</h5>
            </div>
            <form   action="/register" method="post" onsubmit="return false" id="registerform">
            <div class="modal-body">
                <input name="SignEmail" placeholder="Email" class="col-xs-1 form-control input-group-sm" type="email"><p></p>
                <input name="SignUserName" placeholder="Username" class="col-xs-1 form-control input-group-sm" ><p></p>
                <input name="SignPassword" placeholder="Password" class="col-xs-1 form-control input-group-sm" type="password"><p></p>
                <span>Male</span><input type="radio"  name="SignGender" value="male">
                <span>Female</span><input type="radio"  name="SignGender" value="female"><p></p>
                <input name="SignHeight" placeholder="Height(CM)" class="col-xs-1 form-control input-group-sm" type="number" step="0.01"><p></p>
                <input name="SignWeight" placeholder="Weight(KG)" class="col-xs-1 form-control input-group-sm" type="number" step="0.01"><p></p>
                <input name="SignAge" placeholder="Age" class="col-xs-1 form-control input-group-sm" type="number"><p></p>
               <input name="SignTargetDate" placeholder="TargetDate" class="col-xs-2 form-control " type="number"><p></p>
                <input name="SignTargetWeight" placeholder="Target Weight(KG)" class="col-xs-1 form-control input-group-sm" type="number" step="0.01"><p></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-warning" data-dismiss="modal">close</button>
                <button type="submit" class="btn btn-info" onclick="register()" >submit</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>
<style>

    .carousel-item img {
        max-width: 100%;
        height: 22rem;
    }
    input{
        /*border-radius: 5px;*/
        /*height: 100%;*/
    }
    .nav-item {
        font-size: 25px;
    }



</style>