var socket = new WebSocket('ws://localhost:8080/notification');

socket.onopen = function() {

      var username = document.getElementById('loginUsername').value;
      var message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

//댓글달기 버튼에 send 기능
function notificationSend(senderName, receiverName){
    const notificationAddRequestDto = {
        senderName: senderName,
        receiverName: receiverName,
        text: senderName + '님이 댓글을 달았습니다.'
    }

    var json = JSON.stringify(notificationAddRequestDto);

    socket.send(json);
}