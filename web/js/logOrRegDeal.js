var script = document.createElement("script");
script.type = "text/javascript";
script.src="jquery.min.js";
function insertError(container, msg) {
    container.html("<span style='color: red'>" + msg + "</span>");
    container.show();
}

function loadFoam(usernameid, passwordid, username, password) {
    $("#" + usernameid).val(username);
    $("#" + passwordid).val(password);
}