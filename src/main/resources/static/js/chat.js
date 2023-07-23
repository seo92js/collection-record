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
    const json = event.data;
    const chatMessageResponseDto = JSON.parse(json);

    const messageContainer = document.getElementById('message-container');

    const div = document.createElement('div');

    const createdTime = document.createElement('span');
    createdTime.textContent = chatMessageResponseDto.createdTime + ' / ';

    const username = document.createElement('span');
    username.textContent = chatMessageResponseDto.senderName + ' / ';

    const message = document.createElement('span');
    message.textContent = chatMessageResponseDto.message;

    div.appendChild(createdTime);
    div.appendChild(username);
    div.appendChild(message);

    messageContainer.appendChild(div);

    $.ajax({
        type: 'PUT',
        url: '/api/v1/chatmessage-update/' + chatMessageResponseDto.chatRoomId,
        data: formData,
        processData: false,
        contentType: false
    }).done(function(){
        alert('수정완료.');
        location.reload();
    }).fail(function (error){
        alert(JSON.stringify(error));
    });
}

function send(senderId, receiverId, chatRoomId){
    const message = document.getElementById("input-chat").value;

    const chatMessageAddRequestDto = {
        senderId: senderId,
        receiverId: receiverId,
        chatRoomId: chatRoomId,
        message: message,
        read: false
    }

    const json = JSON.stringify(chatMessageAddRequestDto);

    chatSocket.send(json);
}