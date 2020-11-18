function handleExerciseNameQuery() {
    console.log( $('#exerciseName').val());
    var data = {sportName:  $('#exerciseName').val()};

    if ($('#exerciseName').val() != null) {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/ExerciseNameQuery",
            data:data,
            dataType:"json",
            async: false,
            error: function(request) {
                alert("Error");
            },
            success: function(data) {
                $('#possibleSportName').empty();
                var count = 0;
                for (var sport of data) {
                    if (count > 5) {
                        break;
                    }
                    else {
                        var $li=$("<li></li>");
                        $li.className = "list-group-item";
                        $li.text(sport.sportName);
                        $('#possibleSportName').append($li);
                        count += 1;
                    }
                }

            }
        });
    }
}

function handleExerciseCalorieQuery() {
    console.log( $('#exerciseName').val());
    var data = {
        sportName:  $('#exerciseName').val(),
    };
    if($('#exerciseName').val() === "") {
        alert("PLease enter sport name.")
    }
    else if ($("#sportTime").val() === "") {
        alert("PLease enter sport duration.")
    }
    else {
        $.ajax({
            cache: true,
            type: "POST",
            url:"/ExerciseNameQuery",
            data:data,
            dataType:"json",
            async: false,
            error: function(request) {
                alert("Error");
            },
            success: function(data) {
                console.log(data);
                var calori = data[0].cost * parseInt($("#sportTime").val());
                $("#queryResult").text(calori+" kcal");
            }
        });
    }
}

function handleAddRecord() {
    var time = $("#time").val();
    var date = $("#date").val();
    var name = $('#exerciseName').val();
    var amount = $("#sportTime").val();
    var calorie = $("#queryResult").text();
    if(name === "") {
        alert("PLease enter exercise name.");
    }
    else if (amount === "") {
        alert("PLease enter exercise amount.");
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
    $labelLeft.text(name+"    "+amount+" hrs    "+calorie);

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
        url:"/AddExerciseRecord",
        data:data,
        dataType:"json",
        async: false,
        success: function(data) {
            console.log("Add Record - Success.");
            $("#records").append($div);
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
        url:"/RemoveExerciseRecord",
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
        url:"/GetAllExerciseRecords",
        data:obj,
        dataType:"json",
        async: false,
        error: function(request) {
            if ($("input[name='Email']")) {
                alert("Please Login");
            }
            else {
                alert("Get all exercise records - Error");
            }
        },
        success: function(data) {
            console.log("Get all exercise records - Success.");
            console.log(data);
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
                $labelLeft.text(d.sportName+"    "+d.duration+" hrs    "+d.calorie+" kcal");

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