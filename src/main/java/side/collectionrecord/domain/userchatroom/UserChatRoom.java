package side.collectionrecord.domain.userchatroom;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class UserChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    @Builder
    public UserChatRoom(User user, ChatRoom chatRoom){
        this.user = user;
        this.chatRoom = chatRoom;
        user.addUserChatRoom(this);
        chatRoom.addUserChatRoom(this);
    }
}
