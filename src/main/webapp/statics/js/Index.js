

function register() {

    $.ajax({
        cache: true,
        type: "POST",
        url:"/register",
        data:$('#registerform').serialize(),
        async: false,
        error: function(request) {
            alert("Error");
        },
        success: function(data) {
            $('#signup').modal('hide');
            alert("Successfully sign up");

            window.open("/")
        }
    });

    return false;



}

