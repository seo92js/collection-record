var socket = new WebSocket('ws://localhost:8080/notification');

socket.onopen = function() {

      var username = document.getElementById('loginUsername').value;
      var message = {
        type: 'username',
        value: username
      };

      socket.send(JSON.stringify(message));
};

socket.onmessage = function(event) {
    var notificationButton = document.getElementById('notification-btn');

    notificationButton.classList.remove('btn-primary');
    notificationButton.classList.add('btn-warning');

    // 실시간 알림 받거나,

    //홈 화면에서 안읽은 메세지를 체크했을 때

    var jsonArray = JSON.parse(event.data);

    for (var i = 0; i < jsonArray.length; i++){
        var notification = jsonArray[i];
    }
}