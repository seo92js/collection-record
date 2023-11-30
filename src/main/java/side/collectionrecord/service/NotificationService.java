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
import side.collectionrecord.web.dto.CreateNotificationRequestDto;
import side.collectionrecord.web.dto.GetNotificationResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    @Transactional
    public Long createNotification(CreateNotificationRequestDto createNotificationRequestDto){
        User sender = userRepository.findByUsername(createNotificationRequestDto.getSenderName()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));
        User receiver = userRepository.findByUsername(createNotificationRequestDto.getReceiverName()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message(createNotificationRequestDto.getText())
                .confirm(false)
                .url(createNotificationRequestDto.getUrl())
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
    public List<GetNotificationResponseDto> getAllNotificationByUserIdConfirmFalse(Long userId){
        return notificationRepository.findByUserIdConfirmFalse(userId).stream()
                .map(GetNotificationResponseDto::new).collect(Collectors.toList());
    }
}
