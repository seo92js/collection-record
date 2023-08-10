const notificationSocket = new WebSocket('ws://localhost:8080/notification');

notificationSocket.onopen = function() {

      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      notificationSocket.send(JSON.stringify(message));
};

notificationSocket.onmessage = function(event) {
    const notification = document.getElementById('notification');

    notification.classList.add('nav__li--red');
}