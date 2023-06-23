package side.collectionrecord.domain.chatmessage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChatMessage {
    @Id @GeneratedValue
    @Column(name = "chatmessage_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private String message;

    @Builder
    public ChatMessage(User user, ChatRoom chatRoom, String message){
        this.user = user;
        this.chatRoom = chatRoom;
        this.message = message;
    }
}
