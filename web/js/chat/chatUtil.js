
function openWebSocket() {
    var ws = new WebSocket("ws://localhost:8080/LiteTalk/websocket/")
}

function showMsg(container, msg, isSelf) {
    var lClass = isSelf ? "self" : "other";
    var m = "<li class='" + lClass + "'>" + msg.content + "</li>"
    $('#' + container).append(m);
}