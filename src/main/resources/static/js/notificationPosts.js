var socket = new WebSocket('ws://localhost:8080/notification');

socket.onopen = function() {

      var username = document.getElementById('loginUsername').value;
      var message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

function notificationSend(senderName, receiverName, postsId){
    const notificationAddRequestDto = {
        senderName: senderName,
        receiverName: receiverName,
        text: senderName + '님이 댓글을 달았습니다.',
        url: '/posts/' + postsId
    }

    var json = JSON.stringify(notificationAddRequestDto);

    socket.send(json);
}