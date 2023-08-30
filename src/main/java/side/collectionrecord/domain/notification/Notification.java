package side.collectionrecord.domain.notification;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import side.collectionrecord.domain.BaseTimeEntity;
import side.collectionrecord.domain.user.User;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Notification extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String message;

    private boolean read;

    private String url;

    @Builder
    public Notification(User sender, User receiver, String message, boolean read, String url){
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.read = read;
        this.url = url;

        sender.addSendNotify(this);
        receiver.addReceiveNotify(this);
    }

    public void setRead(){
        this.read = true;
    }
}
