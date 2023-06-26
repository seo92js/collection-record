var socket = new WebSocket('ws://localhost:8080/chatroom');

socket.onmessage = function(event) {
    var json = event.data;
    var chatMessageResponseDto = JSON.parse(json);

    var messageContainer = document.getElementById('message-container');

    var username = document.createElement('div');
    username.textContent = chatMessageResponseDto.username;

    var createdTime = document.createElement('div');
    username.textContent = chatMessageResponseDto.createdTime;

    var message = document.createElement('div');
    username.textContent = chatMessageResponseDto.message;

    messageContainer.appendChild(username);
    messageContainer.appendChild(createdTime);
    messageContainer.appendChild(message);
}

function send(userId, chatRoomId){
    var text = document.getElementById("input-chat").value;

    const chatMessageAddRequestDto = {
        userId: userId,
        chatRoomId: chatRoomId,
        text: text
    }

    var json = JSON.stringify(chatMessageAddRequestDto);

    socket.send(json);
}