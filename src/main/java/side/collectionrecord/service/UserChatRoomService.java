package side.collectionrecord.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.chatmessage.ChatMessageRepository;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.QUserChatRoom;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;
import side.collectionrecord.web.dto.GetUserChatRoomResponseDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserChatRoomService {
    private final UserRepository userRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Transactional
    public UserChatRoom createUserChatRoom(Long user1Id, Long user2Id){

        UserChatRoom userChatRoom = getUserChatroom(user1Id, user2Id);

        if (userChatRoom == null){
            User user1 = userRepository.findById(user1Id).get();
            User user2 = userRepository.findById(user2Id).get();

            ChatRoom chatRoom = new ChatRoom();

            chatRoomRepository.save(chatRoom);

            UserChatRoom userChatRoom1 = UserChatRoom.builder()
                    .user(user1)
                    .chatRoom(chatRoom)
                    .build();

            UserChatRoom userChatRoom2 = UserChatRoom.builder()
                    .user(user2)
                    .chatRoom(chatRoom)
                    .build();

            userChatRoomRepository.save(userChatRoom1);
            userChatRoomRepository.save(userChatRoom2);

            return userChatRoom1;
        }else{
            return userChatRoom;
        }
    }

    @Transactional
    public UserChatRoom getUserChatroom(Long user1Id, Long user2Id){

        return userChatRoomRepository.findByUserIds(user1Id, user2Id);
    }

    @Transactional
    public List<GetUserChatRoomResponseDto> getAllUserChatroomByUserId(Long userId){
        List<Tuple> userChatRoomList = userChatRoomRepository.findUserAndChatroomIdByUserId(userId);

        List<GetUserChatRoomResponseDto> responseDtoList = new ArrayList<>();

        for (Tuple tuple : userChatRoomList){
            User user = tuple.get(QUserChatRoom.userChatRoom.user);
            Long chatRoomId = tuple.get(QUserChatRoom.userChatRoom.chatRoom.id);
            List<ChatMessage> readFalseMessage = chatMessageRepository.findByChatroomIdAndUserIdReadFalse(chatRoomId, userId);
            if(readFalseMessage.size() > 0) {
                responseDtoList.add(new GetUserChatRoomResponseDto(user, false));
            }else{
                responseDtoList.add(new GetUserChatRoomResponseDto(user, true));
            }
        }

        return responseDtoList;
    }

    @Transactional
    public boolean checkReadFalseMessage(Long userId){
        User user = userRepository.findById(userId).get();

        for ( UserChatRoom userChatRoom : user.getUserChatRooms()){
            List<ChatMessage> readFalseMessage = chatMessageRepository.findByChatroomIdAndUserIdReadFalse(userChatRoom.getChatRoom().getId(), userId);

            if (readFalseMessage.size() > 0)
                return false;
        }

        return true;
    }
}
