package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.notification.NotificationRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.NotificationAddRequestDto;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    @Transactional
    public Long save(NotificationAddRequestDto notificationAddRequestDto){
        User sender = userRepository.findByUsername(notificationAddRequestDto.getSenderName()).get();
        User receiver = userRepository.findByUsername(notificationAddRequestDto.getReceiverName()).get();

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message(notificationAddRequestDto.getText())
                .read(false)
                .build();

        return notificationRepository.save(notification).getId();
    }
}
