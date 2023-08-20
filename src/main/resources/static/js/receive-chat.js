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
    const chat = document.getElementById('chat');

    chat.classList.add('nav__li--red');
}