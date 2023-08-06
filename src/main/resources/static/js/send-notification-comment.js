const socket = new WebSocket('ws://localhost:8080/notification');

socket.onopen = function() {

      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

function sendNotification(senderName, receiverName, postsId){

    if (senderName == receiverName)
        return;

    const createNotificationRequestDto = {
        senderName: senderName,
        receiverName: receiverName,
        text: senderName + '님이 댓글을 달았습니다.',
        url: '/posts/' + postsId
    }

    const json = JSON.stringify(createNotificationRequestDto);

    socket.send(json);
}