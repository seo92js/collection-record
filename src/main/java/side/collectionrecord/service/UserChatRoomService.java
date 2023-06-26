package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;

@RequiredArgsConstructor
@Service
public class UserChatRoomService {
    private final UserRepository userRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public UserChatRoom createChatRoom(Long user1Id, Long user2Id){

        UserChatRoom userChatRoom = findChatRoom(user1Id, user2Id);

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

            return userChatRoom1;
        }else{
            return userChatRoom;
        }
    }

    @Transactional
    public UserChatRoom findChatRoom(Long user1Id, Long user2Id){

        User user1 = userRepository.findById(user1Id).get();
        User user2 = userRepository.findById(user2Id).get();

        for (UserChatRoom userChatRoom1 : user1.getUserChatRooms()) {
            for (UserChatRoom userChatRoom2 : user2.getUserChatRooms()){
                if (userChatRoom1.getChatRoom().getId() == userChatRoom2.getChatRoom().getId()){
                    return userChatRoom1;
                }
            }
        }

        return null;
    }
}
