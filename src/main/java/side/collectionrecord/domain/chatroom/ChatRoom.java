package side.collectionrecord.domain.chatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatmessage.ChatMessage;
import side.collectionrecord.domain.userchatroom.UserChatRoom;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ChatRoom {
    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;

    @OneToMany(mappedBy = "chatRoom")
    private List<UserChatRoom> userChatRooms = new ArrayList<>();

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> chatMessages = new ArrayList<>();

    public void addUserChatRoom(UserChatRoom userChatRoom){
        this.userChatRooms.add(userChatRoom);
    }

    public void addChatMessage(ChatMessage chatMessage){
        this.chatMessages.add(chatMessage);
    }
}
