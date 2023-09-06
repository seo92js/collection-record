//const socket = new WebSocket('ws://localhost:8080/notification');
const socket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com/notification');

socket.onopen = function() {
      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

socket.onclose = function() {
}

function sendNotification(senderName, receiverName){

    if (senderName == receiverName)
        return;

    const createNotificationRequestDto = {
        senderName: senderName,
        receiverName: receiverName,
        text: senderName + ' 님이 팔로우 하였습니다.',
        url: '/user/' + senderName + '/home'
    }

    const json = JSON.stringify(createNotificationRequestDto);

    socket.send(json);
}