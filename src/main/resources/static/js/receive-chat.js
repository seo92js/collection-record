//const chatSocket = new WebSocket('ws://localhost:8080/chatroom');
const currentPort = window.location.port;
const chatSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:' + currentPort + '/chatroom');

chatSocket.onopen = function() {
      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      chatSocket.send(JSON.stringify(message));
};

chatSocket.onclose = function() {
}

chatSocket.onmessage = function(event) {
    const chat = document.getElementById('chat');

    chat.classList.add('nav__li--red');
}