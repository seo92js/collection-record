package side.collectionrecord.domain.chatmessage;

import side.collectionrecord.domain.user.User;

import java.util.List;

public interface ChatMessageRepositoryCustom {
    List<ChatMessage> findAllMessageByChatRoom(Long chatRoomId);

}
