package side.collectionrecord.domain.chatmessage;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    List<ChatMessage> findAllMessageByChatRoom(Long chatRoomId);

    List<ChatMessage> findNotReadMessage(Long chatRoomId, Long userId);
}
