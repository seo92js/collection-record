package side.collectionrecord.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.notification.NotificationRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.NotificationAddRequestDto;
import side.collectionrecord.web.dto.NotificationResponseDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class NotificationServiceTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

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

        NotificationAddRequestDto notificationAddRequestDto = NotificationAddRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        //when
        notificationService.save(notificationAddRequestDto);

        //then
        List<Notification> all = notificationRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 읽음처리(){
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

        NotificationAddRequestDto notificationAddRequestDto = NotificationAddRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        //when
        Long id = notificationService.save(notificationAddRequestDto);

        notificationService.read(id);

        User user = userRepository.findById(receiver.getId()).get();

        assertThat(user.getReceiveNotify().get(0).isRead()).isEqualTo(true);
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

        NotificationAddRequestDto notificationAddRequestDto = NotificationAddRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        Long id = notificationService.save(notificationAddRequestDto);

        //when
        List<NotificationResponseDto> notReadNotification = notificationService.findNotReadNotification(receiver.getId());

        //then
        assertThat(notReadNotification.size()).isEqualTo(1);
    }
}