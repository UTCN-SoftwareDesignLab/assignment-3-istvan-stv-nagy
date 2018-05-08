var wsocket = new SockJS('/gs-guide-websocket');

var secretary = Stomp.over(wsocket);

function sendName() {

    var date = $("#date");
    var startTime = $("#startTime");
    var endTime = $("#endTime");
    var doctorID = $("#doctor");
    var patientID = $("#patientID");

    var consultation = {date:date[0].value, startTime:startTime[0].value, endTime:endTime[0].value, doctorID:doctorID[0].value, patientID:patientID[0].value};

    secretary.send("/app/hello", {}, JSON.stringify(consultation));
}

$(function () {
// $("form").on('submit', function (e) {
//     e.preventDefault();
// });
 $( "#create" ).click(function() { sendName(); });
});