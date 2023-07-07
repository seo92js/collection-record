var chatSocket = new WebSocket('ws://localhost:8080/chatroom');

chatSocket.onopen = function() {

      var username = document.getElementById('loginUsername').value;
      var message = {
        type: 'username',
        value: username
      };

      chatSocket.send(JSON.stringify(message));
};

chatSocket.onmessage = function(event) {
    var chatroomList = document.getElementById('chatroom-list');

    chatroomList.classList.remove('btn-info');
    chatroomList.classList.add('btn-warning');
}