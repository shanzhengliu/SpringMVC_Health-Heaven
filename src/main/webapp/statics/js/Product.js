
function queryDb(){
    $.ajax({
        url:"/product/searchDatabase",
        type:"post",
        data:{
            "firstLevelFilter":$('#firstFilter option:selected').val(),
            "secondLevelFilter":$('#secondFilter option:selected').val()
        },
        dataType:"json",
        success:function(data){
            if(data.length!==0){
                var msg = "";
                msg += "<tr>"+"<th>"+"Name"+"</th>"+"<th>"+"Price"+"</th>"+"</tr>";
                for ( var i = 0; i < data.length; i++) {
                    msg += "<tr>";
                    msg += "<td>" + data[i].name + "</td>";
                    msg += "<td>" + data[i].price+ "</td>";
                    msg += "</tr>";
                }
                $("#queryResult").html(msg);
            }
        },
        error:function(){
            alert("error in searching database");
        }
    });
}
function searchWebsite(){
    $.ajax({
        url:"https://amazon-price1.p.rapidapi.com/search?keywords="+ "food "+ $('#keywords').val() + "&marketplace=US",
        type:"get",
        async: true,
        crossDomain:true,
        dataType:"json",
        "headers": {
            "x-rapidapi-host": "amazon-price1.p.rapidapi.com",
            "x-rapidapi-key": "5714acb3f1msh1c73e8946d2f14cp15447ejsn03c021185009"
        },
        success:function(data){
            if(data.length!==0){
                var msg = "";
                msg += "<tr>"+"<th>"+"Title"+"</th>"+"<th>"+"Price"+"</th>"+"<th>"+"Image"+"</th>"+"</tr>";
                for ( var i = 0; i < data.length; i++) {
                    msg += "<tr>";
                    msg += "<td> <a href=" + data[i]['detailPageURL'] +">" + data[i]['title'] + "</a> </td>";
                    msg += "<td>" + data[i]['price'] + "</td>";
                    msg += "<td> <img src=" + data[i]['imageUrl'] + " alt='no'"+ " width=150px" + "/> </td>";
                    msg += "</tr>";
                }
                $("#searchResult").html(msg);
            }
        },
        error:function(){
            alert("error in searching website");
        }
    });
}
