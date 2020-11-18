function handleFoodNameQuery() {
    console.log( $('#foodName').val());
    var data = {foodname:  $('#foodName').val()};

    if ($('#foodName').val() != null) {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/FoodNameQuery",
            data:data,
            dataType:"json",
            async: false,
            error: function(request) {
                alert("Error");
            },
            success: function(data) {
                $('#possibleFoodName').empty();
                var count = 0;
                for (var food of data) {
                    if (count > 5) {
                        break;
                    }
                    else {
                        console.log(food.foodname)
                        var $li=$("<li></li>");
                        $li.className = "list-group-item";
                        $li.text(food.foodname);
                        $('#possibleFoodName').append($li);
                        count += 1;
                    }
                }

            }
        });
    }
}

function handleFoodCalorieQuery() {
    var data = {
        foodname:  $('#foodName').val(),
    };
    if($('#foodName').val() === "") {
        alert("PLease enter food name.")
    }
    else if ($("#foodAmount").val() === "") {
        alert("PLease enter food amount.")
    }
    else {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/FoodNameQuery",
            data:data,
            dataType:"json",
            async: false,
            error: function(request) {
                alert("Error");
            },
            success: function(data) {
                var calori = data[0].cost * parseInt($("#foodAmount").val());
                $("#queryResult").text(calori+" kcal");
            }
        });
    }
}

function handleAddRecord() {
    var time = $("#time").val();
    var date = $("#date").val();
    var name = $('#foodName').val();
    var amount = $("#foodAmount").val();
    var calorie = $("#queryResult").text();
    if(name === "") {
        alert("PLease enter food name.");
    }
    else if (amount === "") {
        alert("PLease enter food amount.");
    }
    else if (time === "") {
        alert("Please enter time.");
    }
    else if (date === "") {
        alert("Please enter date");
    }

    console.log(date+"    "+time);

    var $div = $("<div></div>");
    $div.addClass("row");

    var $divBtn= $("<div></div>");
    $divBtn.addClass("col-2");

    var $btn = $("<button></button>");
    $btn.text("Remove");
    $btn.attr('id', "btn/" +date+"/"+time);
    $btn.bind('click', function() {
        handleDeleteRecord("btn/" +date+"/"+time);
    });

    $divBtn.append($btn)

    var $divLeft = $("<div></div>");
    $divLeft.addClass("col-4");

    var $labelLeft = $("<label></label>");
    $labelLeft.text(name+"    "+amount+" serving    "+calorie);

    $divLeft.append($labelLeft)

    var $divRight = $("<div></div>");
    $divRight.addClass("col-6");

    var $labelRight = $("<label></label>");
    $labelRight.text(date+"    "+time);
    $labelRight.attr('id', "label/" +date+"/"+time);

    $divRight.append($labelRight)

    $div.append($divBtn);
    $div.append($divLeft);
    $div.append($divRight);

    // if ($("input[name='Email']")) {
    //
    // }
    // else {
    //     $("#records").append($div);
    // }

    var data = {
        foodname: name,
        amount: parseInt(amount),
        time: time,
        date: date,
        calorie: parseInt(calorie.substring(0,calorie.length-5))
    };

    $.ajax({
        cache: true,
        type: "POST",
        url:"/AddFoodRecord",
        data:data,
        dataType:"json",
        async: false,
        success: function(data) {
            $("#records").append($div);
            console.log("Add Record - Success.");
        },
        error: function() {
            if ($("input[name='Email']")) {
                alert("Please Login");
            }
            else {
                alert("Add Record - Error")
            }
        }
    });
}

function handleDeleteRecord(btnId) {

    // var btnId = $(event.target).attr('id');
    console.log(btnId)
    var para = btnId.split("/");
    var date = para[1];
    var time = para[2];
    var data = {
        time: time,
        date: date,
    };

    $.ajax({
        cache: true,
        type: "POST",
        url:"/RemoveFoodRecord",
        data:data,
        dataType:"json",
        async: false,
        error: function(request) {
            alert("Delete Record - Error.");
        },
        success: function(data) {
            console.log("Delete Record - Success.");
            reloadRecords();
        }
    });
}

function reloadRecords() {
    var obj = {

    };
    $.ajax({
        cache: true,
        type: "POST",
        url:"/GetAllFoodRecords",
        data:obj,
        dataType:"json",
        async: false,
        error: function(request) {
            if ($("input[name='Email']")) {
                alert("Please Login");
            }
            else {
                alert("Get all food records - Error");
            }
        },
        success: function(data) {
            console.log("Get all food records - Success.");
            $("#records").empty();
            for (var d of data) {
                var $div = $("<div></div>");
                $div.addClass("row");

                var $divBtn= $("<div></div>");
                $divBtn.addClass("col-2");

                var $btn = $("<button></button>");
                $btn.text("Remove");
                $btn.attr('id', "btn/"+d.date+"/"+d.time);
                $btn.bind('click', function() {
                    handleDeleteRecord("btn/"+d.date+"/"+d.time);
                });

                $divBtn.append($btn)

                var $divLeft = $("<div></div>");
                $divLeft.addClass("col-4");

                var $labelLeft = $("<label></label>");
                $labelLeft.text(d.foodname+"    "+d.amount+" serving    "+d.calorie+" kcal");

                $divLeft.append($labelLeft)

                var $divRight = $("<div></div>");
                $divRight.addClass("col-6");

                var $labelRight = $("<label></label>");
                $labelRight.text(d.date+"    "+d.time);
                $labelRight.attr('id', "label/"+d.date+"/"+d.time);

                $divRight.append($labelRight)

                $div.append($divBtn);
                $div.append($divLeft);
                $div.append($divRight);

                $("#records").append($div);
            }
        }
    });
}