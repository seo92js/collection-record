const chatSocket = new WebSocket('ws://localhost:8080/chatroom');

chatSocket.onopen = function() {

      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      chatSocket.send(JSON.stringify(message));
};

chatSocket.onmessage = function(event) {
    const chatroomList = document.getElementById('chatroom-list');

    chatroomList.classList.remove('btn-info');
    chatroomList.classList.add('btn-warning');
}