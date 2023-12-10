package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.notification.NotificationRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.NotificationNotFoundException;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.NotificationRequestDto;
import side.collectionrecord.web.dto.NotificationResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    @Transactional
    public Long createNotification(NotificationRequestDto notificationRequestDto){
        User sender = userRepository.findByUsername(notificationRequestDto.getSenderName()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User receiver = userRepository.findByUsername(notificationRequestDto.getReceiverName()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message(notificationRequestDto.getText())
                .confirm(false)
                .url(notificationRequestDto.getUrl())
                .build();

        return notificationRepository.save(notification).getId();
    }

    @Transactional
    public Long updateNotification(Long id){
        Notification notification = notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("알림이 없습니다."));

        notification.setConfirm();

        return id;
    }

    @Transactional
    public List<NotificationResponseDto> getAllNotificationByUserIdConfirmFalse(Long userId){
        return notificationRepository.findByUserIdConfirmFalse(userId).stream()
                .map(NotificationResponseDto::new).collect(Collectors.toList());
    }
}
