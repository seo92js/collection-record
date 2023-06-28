var socket = new WebSocket('ws://localhost:8080/chatroom');

socket.onmessage = function(event) {
    var json = event.data;
    var chatMessageResponseDto = JSON.parse(json);

    var messageContainer = document.getElementById('message-container');

    var div = document.createElement('div');

    var createdTime = document.createElement('span');
    createdTime.textContent = chatMessageResponseDto.createdTime + ' / ';

    var username = document.createElement('span');
    username.textContent = chatMessageResponseDto.username + ' / ';

    var message = document.createElement('span');
    message.textContent = chatMessageResponseDto.message;

    div.appendChild(createdTime);
    div.appendChild(username);
    div.appendChild(message);

    messageContainer.appendChild(div);
}

function send(userId, chatRoomId){
    var message = document.getElementById("input-chat").value;

    const chatMessageAddRequestDto = {
        userId: userId,
        chatRoomId: chatRoomId,
        message: message
    }

    var json = JSON.stringify(chatMessageAddRequestDto);

    socket.send(json);
}