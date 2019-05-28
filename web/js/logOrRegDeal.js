var script = document.createElement("script");
script.type = "text/javascript";
script.src="jquery.min.js";
function dealError() {
    var usernameInfo = "${usernameError}";
    var passwordInfo = "${passwordError}";
    var loginInfo = "${error}";
    if (usernameInfo !== '') {
        $('#username-error').html("<span color='red'>" + usernameInfo + "</span>");
    }
    if (passwordInfo !== '') {
        $('#password-error').html("<span color='red'>" + passwordInfo + "</span>");
    }
    if (loginInfo !== '') {
        $('#login-error').html("<span color='red'>" + loginInfo + "</span>");
    }
}

function loadFoam(usernameid, passwordid) {
    var username = "${username}";
    var password = "{password}";
    $('#' + usernameid).val(username);
    $('#' + passwordid).val(password);
}