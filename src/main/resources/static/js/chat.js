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
    if (event.data != 'send'){
        const json = event.data;
        const chatMessageResponseDto = JSON.parse(json);

        $.ajax({
            type: 'PUT',
            url: '/api/v1/chatmessage-update/' + chatMessageResponseDto.chatRoomId,
        }).done(function(){
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
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