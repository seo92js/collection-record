package side.collectionrecord.domain.chatmessage;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.chatroom.ChatRoom;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ChatMessage extends BaseTimeEntity {
    @Id @GeneratedValue
    @Column(name = "chatmessage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private ChatRoom chatRoom;

    private String message;

    private boolean read;

    @Builder
    public ChatMessage(User sender, User receiver, ChatRoom chatRoom, String message, boolean read){
        this.sender = sender;
        this.receiver = receiver;
        this.chatRoom = chatRoom;
        this.message = message;
        this.read = read;

        sender.addSendMessage(this);
        receiver.addReceiveMessage(this);

        chatRoom.addChatMessage(this);
    }

    public void updateRead(){
        this.read = true;
    }
}
