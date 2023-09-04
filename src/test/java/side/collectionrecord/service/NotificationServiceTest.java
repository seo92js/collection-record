package side.collectionrecord.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.notification.NotificationRepository;
import side.collectionrecord.domain.user.Role;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreateNotificationRequestDto;
import side.collectionrecord.web.dto.GetNotificationResponseDto;

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
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(sender);

        User receiver = User.builder()
                .username("receiver")
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(receiver);

        CreateNotificationRequestDto createNotificationRequestDto = CreateNotificationRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        //when
        notificationService.createNotification(createNotificationRequestDto);

        //then
        List<Notification> all = notificationRepository.findAll();

        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void 읽음처리(){
        //given
        User sender = User.builder()
                .username("sender")
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(sender);

        User receiver = User.builder()
                .username("receiver")
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(receiver);

        CreateNotificationRequestDto createNotificationRequestDto = CreateNotificationRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        //when
        Long id = notificationService.createNotification(createNotificationRequestDto);

        notificationService.updateNotification(id);

        User user = userRepository.findById(receiver.getId()).get();

        assertThat(user.getReceiveNotify().get(0).isConfirm()).isEqualTo(true);
    }

    @Test
    public void 안읽은_알림찾기(){
        //given
        User sender = User.builder()
                .username("sender")
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(sender);

        User receiver = User.builder()
                .username("receiver")
                .profileImage(null)
                .role(Role.USER)
                .profileText(null)
                .email("email")
                .build();

        userRepository.save(receiver);

        CreateNotificationRequestDto createNotificationRequestDto = CreateNotificationRequestDto.builder()
                .senderName(sender.getUsername())
                .receiverName(receiver.getUsername())
                .text("text")
                .url("url")
                .build();

        Long id = notificationService.createNotification(createNotificationRequestDto);

        //when
        List<GetNotificationResponseDto> notConfirmNotification = notificationService.getAllNotificationByUserIdConfirmFalse(receiver.getId());

        //then
        assertThat(notConfirmNotification.size()).isEqualTo(1);
    }
}