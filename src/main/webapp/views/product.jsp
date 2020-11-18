<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: onepunchmanpk
  Date: 9/10/20
  Time: 10:30 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome to products page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="../statics/js/Product.js"></script>
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
        <h1>PRODUCT</h1>
    </div>
</section>

<%--<ul>--%>
<%--    <c:forEach var="product" items="${products}">--%>
<%--        <li>${product}</li>--%>
<%--    </c:forEach>--%>
<%--</ul>--%>
<div id="container">
<div id="query">
    <h2>Products database</h2>
    Filter brands and organs
    <select id="firstFilter" name="firstLevelFilter" >
        <option value="All"> All </option>
        <c:forEach var="brand" items="${brands}">
            <option value=${brand}>${brand}</option>
        </c:forEach>
    </select>

    <select id="secondFilter" name="secondLevelFilter" >
        <option value="All"> All </option>
        <c:forEach var="organ" items="${organs}">
            <option value=${organ}>${organ}</option>
        </c:forEach>
    </select>
    <input type ="submit" value="search" id="DBbutton" onclick="queryDb()">
    <table id="queryResult" >
    </table>
</div>



<div id="onlineSearch">
    <h2>Search Amazon</h2>
    <input type="text" placeholder="Input product keywords" id="keywords"/>
    <input type="submit" value="search" onclick="searchWebsite()"/>
    <table id="searchResult">
    </table>
</div>

</div>
</body>
</html>

<style>

    td{
        max-width: 700px;
        word-wrap: break-word;
    }

    .carousel-item img {
        max-width: 100%;
        height: 22rem;
    }

    .nav-item {
        font-size: 25px;
    }

    h2 {
        font-family: Helvetica, Arial, sans-serif
    }
    #queryResult, #searchResult {
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;
        font-family: sans-serif;
        min-width: 400px;
        box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);
    }

    #queryResult th, #searchResult th {
        background-color: #51adcf;
        color: #ffffff;
        text-align: left;
    }

    #queryResult th, #queryResult td, #searchResult th, #searchResult td{
        padding: 12px 15px;
    }

    #queryResult tr,#searchResult tr {
        border-bottom: 1px solid #dddddd;
    }

    #queryResult tr:nth-of-type(even), #searchResult tr:nth-of-type(even) {
        background-color: #f3f3f3;
    }

    #queryResult tr:last-of-type, #searchResult tr:last-of-type {
        border-bottom: 2px solid #0278ae;
    }
    #container{
        margin: auto;
        width: 70%;
        border: 3px solid #0278ae;
        padding: 10px;
    }

</style>