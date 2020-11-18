function updatePosts(){
    $.ajax({
        url:"/updatePosts",
        type:"post",
        error:function (request){
            alert("Can not load post from database");
        },
        success:function (data){
            if(data.length!==0){
                let msg="";
                msg+= "<tr>\n " +"<td style=\"font-weight:bold\"> Title </td>\n"
                    + '<td style="font-weight:bold"> Tag </td>\n'
                    +'<td style="font-weight:bold"> Content </td>\n' +"</tr>"
                for(let i=0;i<data.length;i++){
                    msg+="<tr class='tableIndex'>";
                    msg+='<td >'+data[i].title+'</td>';
                    msg+='<td style="font-weight:bold">'+data[i].tag+'</td>';
                    msg+='<td>'+cutString(data[i].content, 89)+'</td>';
                    msg+='<td bgcolor="#f3f3f3"><button id= "bt0" class="btn btn btn-info" type="button" data-toggle="modal" data-target="#content" data-whatever=data[i].content >Show All</button></td>';

                    msg += "</tr>";
                }
                $("#postTable").html(msg);

                $('.tableIndex').click(function () {
                    var a = $('.tableIndex').index(this);
                    $('#content').on('show.bs.modal', function (event) {
                        var button = $(event.relatedTarget)

                        var title = data[a].title
                        var tag = data[a].tag
                        var content = data[a].content
                        var modal = $(this)

                        modal.find('.modal-title').text(title)
                        modal.find(".postTag").text(tag)
                        modal.find('.showContent').text(content)
                    })


                })

            }
        }
    });
}

function publishPost(){

    $.ajax({
        cache: true,
        url:"/publishPosts",
        type:"post",
        data:$('#publishform').serialize(),
        async: false,
        error:function (request){
             alert("Publish failed ");

        },
        success:function (data){
            alert("Successful");
        }
    });
}

function cutString(str, len) {
    if(str.length*2 <= len) {
        return str;
    }
    var strlen = 0;
    var s = "";
    for(var i = 0;i < str.length; i++) {
        s = s + str.charAt(i);
        if (str.charCodeAt(i) > 128) {
            strlen = strlen + 2;
            if(strlen >= len){
                return s.substring(0,s.length-1) + "...";
            }
        } else {
            strlen = strlen + 1;
            if(strlen >= len){
                return s.substring(0,s.length-2) + "...";
            }
        }
    }
    return s;
}