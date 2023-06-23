package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;

@RequiredArgsConstructor
@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

    private final UserChatRoomRepository userChatRoomRepository;
}
