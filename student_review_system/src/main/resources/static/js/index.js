$('#submit').on('click',log);
$('#pwd').bind('keyup', function(event) {
    if (event.keyCode == "13") {
        $('#submit').click();
    }
});
function log() {
    var username = $('#id').val().trim().replace(/\s/g,"");
    var pwd = $('#pwd').val().trim().replace(/\s/g,"");
    if (username == '' ||username == null){
        $('#idTitle').show();
    }else if (pwd == '' || pwd == null){
        $('#idTitle').hide();
        $('#pwdTitle').show();
    } else {
        $('#idTitle').hide();
        $.ajax({
            type: "POST",
            url: "http://47.97.206.40/login",
            data: {
                "username": username,
                "password": pwd
            },
            // contentType: "application/json",
            // dataType: "json",
            complete: function (response) {
                console.log(response.responseText);
                var res = JSON.parse(response.responseText);
                var state = res.state;


                if (state == '' || state == null) {
                    $('#idTitle').hide();
                    $('#pwdTitle').show();
                }else if(state == 'success'){
                    // localStorage.setItem("name",'David');
                    // localStorage.setItem("authority",'student');
                    localStorage.setItem("name",res.name);
                    localStorage.setItem("id",username);
                    localStorage.setItem("authority",res.authority);
                    if (res.authority == 'admin') {
                        alert('你好，欢迎使用spoc系统');
                        $(location).attr('href', 'http://47.97.206.40/admin');
                    }else{
                        $(location).attr('href', 'http://47.97.206.40/course/mainPage');
                    }
                }else {
                    alert('网络异常，请刷新页面！');
                }
            }
        });
    }
}
//
// function isEmpty(val) {
//     return val.length == 0 ? true : false;
// }
//
// function hasSpaceBar(val) {
//     for (var i = 0; i < val.length; i++) {
//         if (val[i] == ' ') return true;
//     }
//     return false;
// }
//
// $(function() {
//     $("#id").on("keyup", function(e) {
//         if (e.which == 13)
//             $("#submit").click();
//     });
//     $("#pwd").on("keyup", function(e) {
//         if (e.which == 13)
//             $("#submit").click();
//     });
//     $("#submit").on("click", function() {
//         let id = $("#id").val();
//         let pwd = $("#pwd").val();
//         let idTitle = $("#idTitle");
//         let pwdTitle = $("#pwdTitle");
//         if (!isEmpty(id) && !isEmpty(pwd)) {
//             if (!hasSpaceBar(id) && !hasSpaceBar(pwd)) {
//                 idTitle.hide();
//                 pwdTitle.hide();
//                 $.post("http://www.stydehome.com/login", {
//                         "id": id,
//                         "password": pwd
//                     },
//                     function(data, status) {
//                         // alert(data);
//                         if (data.authority == null) {
//                             idTitle.hide();
//                             pwdTitle.show();
//                             // alert(data.errorInfo);
//                         } else if (data.authority == "admin") {
//                             $(window).attr("location", "/admin");
//                         } else {
//                             // localStorage.setItem("name",'赵升阳');
//                             // localStorage.setItem("authority",'student');
//                             localStorage.setItem("name",data.name);
//                             localStorage.setItem("authority",data.authority);
//                             $(window).attr("location", "/course/mainPage");
//                         }
//                     })
//             } else if (hasSpaceBar(id)) {
//                 pwdTitle.hide();
//                 idTitle.show();
//             } else if (hasSpaceBar(pwd)) {
//                 idTitle.hide();
//                 pwdTitle.show();
//             }
//         } else if (isEmpty(id)) {
//             pwdTitle.hide();
//             idTitle.show();
//         } else if (isEmpty(pwd)) {
//             idTitle.hide();
//             pwdTitle.show();
//         }
//     })
// })