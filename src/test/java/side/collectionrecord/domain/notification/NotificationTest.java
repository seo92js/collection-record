package side.collectionrecord.domain.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRole;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NotificationTest {
    @Test
    public void 연관관계_세팅(){
        //given
        User sender = User.builder()
                .username("sender")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        User receiver = User.builder()
                .username("receiver")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .read(false)
                .url("url")
                .build();

        //when
        //then
        assertThat(sender.getSendNotify().get(0).getMessage()).isEqualTo("message");
        assertThat(receiver.getReceiveNotify().get(0).getMessage()).isEqualTo("message");
    }

    @Test
    public void 빌더패턴(){
        //given
        User sender = User.builder()
                .username("sender")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        User receiver = User.builder()
                .username("receiver")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .read(false)
                .url("url")
                .build();

        //when
        //then
        assertThat(notification.getSender().getUsername()).isEqualTo("sender");
        assertThat(notification.getReceiver().getUsername()).isEqualTo("receiver");
    }
}