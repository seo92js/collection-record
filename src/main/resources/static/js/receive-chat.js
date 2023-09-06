//const chatSocket = new WebSocket('ws://localhost:8080/chatroom');
const chatSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:8080/chatroom');

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