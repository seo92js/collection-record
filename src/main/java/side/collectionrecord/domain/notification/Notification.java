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
    @Id @GeneratedValue
    @Column(name = "notification_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String message;

    private boolean read;

    @Builder
    public Notification(User user, String message, boolean read){
        this.user = user;
        this.message = message;
        this.read = read;

        user.addNotification(this);
    }
}
