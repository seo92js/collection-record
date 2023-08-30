/*const notificationSocket = new WebSocket('ws://localhost:8080/notification');*/
const notificationSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:8080/notification');

notificationSocket.onopen = function() {
      console.log('open 됨 receive-notification');

      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      notificationSocket.send(JSON.stringify(message));
    console.log('send 됨 receive-notification');
};

notificationSocket.onmessage = function(event) {
    console.log('onmessage 됨 receive-notification');

    const notification = document.getElementById('notification');

    notification.classList.add('nav__li--red');
}

notificationSocket.onclose = function(event) {
    console.log('onclose 됨 receive-notification');
}