//const chatSocket = new WebSocket('ws://localhost:8080/chatroom');
const chatSocket = new WebSocket('ws://ec2-52-79-198-114.ap-northeast-2.compute.amazonaws.com/chatroom');

$('#message-container').scrollTop($('#message-container')[0].scrollHeight);

chatSocket.onopen = function() {
      const username = document.getElementById('loginUsername').value;
      const message = {
        type: 'username',
        value: username
      };
      chatSocket.send(JSON.stringify(message));
}

chatSocket.onclose = function() {
}

chatSocket.onmessage = function(event) {
    if (event.data != 'send'){
        const json = event.data;
        const getChatMessageResponseDto = JSON.parse(json);

        $.ajax({
            type: 'PUT',
            url: '/api/v1/chat-message/' + getChatMessageResponseDto.chatRoomId,
        }).done(function(){
            location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

function send(senderId, receiverId, chatRoomId){
    const message = document.getElementById("input-chat").value;

    const createChatMessageRequestDto = {
        senderId: senderId,
        receiverId: receiverId,
        chatRoomId: chatRoomId,
        message: message,
        confirm: false
    }

    const json = JSON.stringify(createChatMessageRequestDto);

    chatSocket.send(json);
}