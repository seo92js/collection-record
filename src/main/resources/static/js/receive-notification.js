const notificationSocket = new WebSocket('ws://localhost:8080/notification');
/*const notificationSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:8080/notification');*/

notificationSocket.onopen = function() {
      console.log('socket open notification');
      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      notificationSocket.send(JSON.stringify(message));
};

notificationSocket.onclose = function() {
      console.log('socket close notification');
}

notificationSocket.onmessage = function(event) {
    const notification = document.getElementById('notification');

    notification.classList.add('nav__li--red');
}