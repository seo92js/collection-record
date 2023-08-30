/*const chatSocket = new WebSocket('ws://localhost:8080/chatroom');*/
const chatSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com:8080/chatroom');

chatSocket.onopen = function() {
      console.log('open 됨 receive-chat');

      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };

      chatSocket.send(JSON.stringify(message));
    console.log('send 됨 receive-chat');
};

chatSocket.onmessage = function(event) {
    console.log('onmessage 됨 receive-chat');

    const chat = document.getElementById('chat');

    chat.classList.add('nav__li--red');
}

chatSocket.onclose = function(event) {
    console.log('onclose 됨 receive-chat');
     alert('onclose 됨 receive-chat');
}