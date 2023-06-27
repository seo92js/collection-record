package side.collectionrecord.domain.chatmessage;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    public List<ChatMessage> findAllMessageByChatRoom(Long chatRoomId);
}
