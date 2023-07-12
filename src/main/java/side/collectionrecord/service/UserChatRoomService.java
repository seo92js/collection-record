package side.collectionrecord.service;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.chatroom.ChatRoomRepository;
import side.collectionrecord.domain.chatroom.QChatRoom;
import side.collectionrecord.domain.user.QUser;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.userchatroom.UserChatRoom;
import side.collectionrecord.domain.userchatroom.UserChatRoomRepository;
import side.collectionrecord.web.dto.UserChatRoomListResponseDto;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserChatRoomService {
    private final UserRepository userRepository;
    private final UserChatRoomRepository userChatRoomRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public UserChatRoom createChatRoom(Long user1Id, Long user2Id){

        UserChatRoom userChatRoom = findUserChatRoom(user1Id, user2Id);

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
    public UserChatRoom findUserChatRoom(Long user1Id, Long user2Id){

        return userChatRoomRepository.findUserChatRoom(user1Id, user2Id);
    }

    @Transactional
    public List<UserChatRoomListResponseDto> findUserChatRoomList(Long userId){

        List<Tuple> userChatRoomList = userChatRoomRepository.findUserChatRoomList(userId);

        List<UserChatRoomListResponseDto> responseDtoList = new ArrayList<>();

        for (Tuple tuple : userChatRoomList){
            User user = tuple.get(QUser.user);
            Long chatRoomId = tuple.get(QChatRoom.chatRoom.id);
            Boolean read = chatRoomRepository.readAllMessage(chatRoomId, userId);

            responseDtoList.add(new UserChatRoomListResponseDto(user, read));
        }

        return responseDtoList;
    }

    @Transactional
    public boolean readAllMessage(Long userId){
        User user = userRepository.findById(userId).get();

        for ( UserChatRoom userChatRoom : user.getUserChatRooms()){
            Boolean read = chatRoomRepository.readAllMessage(userChatRoom.getChatRoom().getId(), userId);

            if (read == false)
                return false;
        }

        return true;
    }
}
