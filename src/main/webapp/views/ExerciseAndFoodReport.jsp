<%--
  Created by IntelliJ IDEA.
  User: onepunchmanpk
  Date: 27/10/20
  Time: 9:46 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Food and Exercise Report</title>
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
    <script>
        function mysetting() {
            console.log("go to setting");
            window.open("/setting");
            return false;

        }

        var chart = null;
        var dataPoints = [];

        var chart2 = null;
        var dataPoints2 = [];
        var chart3 = null;
        var dataPoints3 = [];

        window.onload = function () {
            alert("loading user data pls wait seconds!")
            chart = new CanvasJS.Chart("chartOne", {
                animationEnabled: true,
                theme: "light2",
                title: {
                    text: "Daily Calorie Consumption"
                },
                axisY: {
                    title: "Calorie",
                    titleFontSize: 24
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,### Calorie",
                    dataPoints: dataPoints
                }]
            });

            chart2 = new CanvasJS.Chart("chartTwo", {
                animationEnabled: true,
                exportEnabled: true,
                theme: "dark1", // "light1", "light2", "dark1", "dark2"
                title: {
                    text: "Evil food"
                },
                axisY: {
                    title: "Calorie",
                    suffix: " g/ml",
                    reversed: true
                },
                axisX2: {
                    tickThickness: 0,
                    labelAngle: 0
                },
                data: [{
                    type: "column",
                    axisXType: "secondary",
                    yValueFormatString: "#,##0 g/ml",
                    dataPoints: dataPoints2
                }]
            });

            chart3 = new CanvasJS.Chart("chartThree", {
                animationEnabled: true,
                exportEnabled: true,
                theme: "light1", // "light1", "light2", "dark1", "dark2"
                title: {
                    text: "Efficient sport"
                },
                axisY: {
                    title: "Calorie",
                    suffix: " g/ml",
                    reversed: true
                },
                axisX2: {
                    tickThickness: 0,
                    labelAngle: 0
                },
                data: [{
                    type: "column",
                    axisXType: "secondary",
                    yValueFormatString: "#,##0 calorie/per",
                    dataPoints: dataPoints3
                }]
            });

            $.ajax({
                url: "/report/getChartsData",
                type: "get",
                dataType: "json",
                success: function (data) {
                    const chart_one_data = objToList(sortByDate(data['chart_one']));
                    const chart_two_data = sortByValueDESC(data['chart_two']);
                    const chart_three_data = sortByValueDESC(data['chart_three']);
                    console.log(chart_one_data);
                    console.log(chart_two_data);
                    console.log(chart_three_data);
                    // only show the most recent 7 days
                    for (var i = chart_one_data.length - 1, count = 0; i >= 0 && count < 7; i--, count++) {
                        dataPoints.push({
                            x: new Date(chart_one_data[i][0]),
                            y: chart_one_data[i][1]
                        });
                    }
                    for (var j = 0, count2 = 0; j < chart_two_data.length && count2 < 7; j++, count2++) {
                        if (j === 0) {
                            dataPoints2.push({
                                y: chart_two_data[j][1],
                                label: chart_two_data[j][0],
                                indexLabel: "Most Evil food!"
                            });
                        } else {
                            dataPoints2.push({
                                y: chart_two_data[j][1],
                                label: chart_two_data[j][0]
                            });
                        }
                    }
                    for (var k = 0, count3 = 0; k < chart_three_data.length && count3 < 7; k++, count3++) {
                        if (k === 0) {
                            dataPoints3.push({
                                y: chart_three_data[k][1],
                                label: chart_three_data[k][0],
                                indexLabel: "Most Efficient Sport!"
                            });
                        } else {
                            dataPoints3.push({
                                y: chart_three_data[k][1],
                                label: chart_three_data[k][0]
                            });
                        }
                    }
                    chart.render();
                    chart2.render();
                    chart3.render();
                },
                error: function () {
                    alert("error in loading charts data");
                }
            });
        }

        function sortByDate(unordered) {
            const ordered = {};
            Object.keys(unordered).sort().forEach(function (key) {
                ordered[key] = unordered[key];
            });
            return ordered;
        }

        function sortByValueDESC(unordered) {
            return Object.entries(unordered).sort((a, b) => b[1] - a[1])
        }

        function sortByValueASC(unordered) {
            return Object.entries(unordered).sort((a, b) => a[1] - b[1])
        }

        function objToList(obj) {
            return Object.keys(obj).map((key) => [key, obj[key]]);
        }
    </script>
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
        <h1>REPORT</h1>
    </div>
</section>
<div id="chartOne" style="height: 370px; max-width: 1320px; margin: 0px auto;">
    <h2>No daily data stored!</h2>
</div>
<div id="chartTwo" style="height: 370px; max-width: 1320px; margin: 0px auto;">
    <h2>No food data stored!</h2>
</div>
<div id="chartThree" style="height: 370px; max-width: 1320px; margin: 0px auto;">
    <h2>No sport data stored!</h2>
</div>
<script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
<script src="../statics/js/canvasjs.min.js"></script>
</body>
</html>
