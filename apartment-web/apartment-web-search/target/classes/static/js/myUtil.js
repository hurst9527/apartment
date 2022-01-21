function pormptBox(times, showText) {
    $("#PromptBox").html(showText);
    $("#PromptBox").show(380);
    $("#PromptBox").delay(times).hide(380);
}

function getCookie(name) {
    var prefix = name + "="
    var start = document.cookie.indexOf(prefix)

    if (start == -1) {
        return null;
    }

    var end = document.cookie.indexOf(";", start + prefix.length)
    if (end == -1) {
        end = document.cookie.length;
    }

    var value = document.cookie.substring(start + prefix.length, end)
    return unescape(value);
}


function setCookie(c_name, value, expiredays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + expiredays);
    document.cookie = c_name + "=" + escape(value) + ((expiredays == null) ? "" : ";expires=" + exdate
        .toGMTString());
}


var nowUrl = window.location.href.split("/")[window.location.href.split("/").length-1]
var userName = getCookie("userName");
var userHeadImg = getCookie("userHeadImg");
if (userHeadImg == null) {
    userHeadImg = "images/layout_img/user_img.jpg";
}
$("#userHeadImg").attr("src", userHeadImg);
if (userName != null) {
    $("#userName").html(userName);
}
// alert(nowUrl)
if ((userName == null)) {
    if (nowUrl != "login.html") {
        window.location.href="login.html"
    }
}