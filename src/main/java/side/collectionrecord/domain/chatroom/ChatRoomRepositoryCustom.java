package side.collectionrecord.domain.chatroom;

public interface ChatRoomRepositoryCustom {
    boolean readAllMessage(Long chatRoomId, Long receiverId);
}
