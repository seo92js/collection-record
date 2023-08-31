package side.collectionrecord.domain.notification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class NotificationRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Test
    public void save(){
        //given
        User sender = User.builder()
                .username("sender")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(sender);

        User receiver = User.builder()
                .username("receiver")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(receiver);

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .read(false)
                .url("url")
                .build();

        notificationRepository.save(notification);

        //when
        List<Notification> all = notificationRepository.findAll();
        //then
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 안읽은_알림찾기(){
        //given
        User sender = User.builder()
                .username("sender")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(sender);

        User receiver = User.builder()
                .username("receiver")
                .password("1")
                .profileImage(null)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(receiver);

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message("message")
                .read(false)
                .url("url")
                .build();

        notificationRepository.save(notification);

        //when
        List<Notification> notReadNotification = notificationRepository.findByUserIdReadFalse(receiver.getId());

        //when
        assertThat(notReadNotification.size()).isEqualTo(1);

        Notification notification1 = notificationRepository.findById(notification.getId()).get();

        notification1.setRead();

        List<Notification> notReadNotification1 = notificationRepository.findByUserIdReadFalse(receiver.getId());

        assertThat(notReadNotification1.size()).isEqualTo(0);
    }
}