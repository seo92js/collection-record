
function connectToChatRoom(user1Id, user2Id){
    var socket = new WebSocket('ws://localhost:8080/chat/' + user1Id + '/' + user2Id);
}