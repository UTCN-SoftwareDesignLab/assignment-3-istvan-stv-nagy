var wsocket = new SockJS('/gs-guide-websocket');

var doctor = Stomp.over(wsocket);

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message.content + "</td></tr>");
}

doctor.connect({}, function(fname) {
    doctor.subscribe('/topic/greetings', function(greeting) {
        showGreeting(JSON.parse(greeting.body));
    });
});