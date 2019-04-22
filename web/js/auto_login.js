
var register = {
    onfocusout: function(element) { $(element).valid(); },
    rules: {
        username: {
            required: true,
            minlength: 2,
            maxlength: 10,
            remote: {
                url: 'LoginServlet?op=ajax',
                type: 'post',
                contentType: 'application/x-www-form-urlencoded',
                data: {
                    username: function () {
                        return $("#username").val();
                    }
                }
            }
        },
        password: {
            required: true,
            minlength: 6,
            maxlength: 10
        },
    },
    messages: {
        username: {
            required: '<font size="3px" color="red">必填</font>',
            remote: '<font size="3px" color="red">被占用</font>',
            minlength: '<font size="3px" color="red">不能低于2位</font>',
            maxlength: '<font size="3px" color="red">不能低于10位</font>',
        },
        password: {
            required: '<font size="3px" color="red">必填</font>',
            minlength: '<font size="3px" color="red">不能低于6位</font>',
            maxlength: '<font size="3px" color="red">不能低于10位</font>',
        },
    }
};

$(function (){
    $('#from1').validate(register);
});


function  remember (){
    var flag = $('#repassword').prop('checked');
    var name = $('#username').val();
    var  repwd = $('#password').val();

    if (flag == true) {
        $.cookie('name',name);
        $.cookie('repwd',repwd);
        $.cookie('flag',flag);
        alert(flag)
    }else {
        $.removeCookie('name');
        $.removeCookie('repwd');
        $.removeCookie('flag');
        alert(flag)
    }
}
 $(function () {
    $('#username').val($.cookie('name'));
    $('#password').val($.cookie('repwd'));
    $('#repassword').prop('checked',$.cookie('flag'))
 });

