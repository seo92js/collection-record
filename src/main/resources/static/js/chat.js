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
    var message = document.getElementById("input-chat").value;

    const chatMessageAddRequestDto = {
        senderId: senderId,
        receiverId: receiverId,
        chatRoomId: chatRoomId,
        message: message,
        read: false
    }

    var json = JSON.stringify(chatMessageAddRequestDto);

    chatSocket.send(json);
}