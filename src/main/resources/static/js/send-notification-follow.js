//const socket = new WebSocket('ws://localhost:8080/notification');
const socket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:8080/notification');

socket.onopen = function() {
      console.log('socket open notification-follow');
      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

socket.onclose = function() {
      console.log('socket close notification-follow');
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