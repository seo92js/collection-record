var socket = new WebSocket('ws://localhost:8080/chatroom');

socket.onopen = function() {

      var username = document.getElementById('username').value;
      var message = {
        type: 'username',
        value: username
      };
      socket.send(JSON.stringify(message));
};

socket.onmessage = function(event) {
    var json = event.data;
    var chatMessageResponseDto = JSON.parse(json);

    var messageContainer = document.getElementById('message-container');

    var div = document.createElement('div');

    var createdTime = document.createElement('span');
    createdTime.textContent = chatMessageResponseDto.createdTime + ' / ';

    var username = document.createElement('span');
    username.textContent = chatMessageResponseDto.senderName + ' / ';

    var message = document.createElement('span');
    message.textContent = chatMessageResponseDto.message;

    div.appendChild(createdTime);
    div.appendChild(username);
    div.appendChild(message);

    messageContainer.appendChild(div);
}

function send(senderId, receiverId, chatRoomId){
    var message = document.getElementById("input-chat").value;

    const chatMessageAddRequestDto = {
        senderId: senderId,
        receiverId: receiverId,
        chatRoomId: chatRoomId,
        message: message
    }

    var json = JSON.stringify(chatMessageAddRequestDto);

    socket.send(json);
}