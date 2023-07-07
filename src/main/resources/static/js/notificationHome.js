var notificationSocket = new WebSocket('ws://localhost:8080/notification');

notificationSocket.onopen = function() {

      var username = document.getElementById('loginUsername').value;
      var message = {
        type: 'username',
        value: username
      };

      notificationSocket.send(JSON.stringify(message));
};

notificationSocket.onmessage = function(event) {
    var notificationButton = document.getElementById('notification-btn');

    notificationButton.classList.remove('btn-primary');
    notificationButton.classList.add('btn-warning');
}