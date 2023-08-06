package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.notification.Notification;
import side.collectionrecord.domain.notification.NotificationRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreateNotificationRequestDto;
import side.collectionrecord.web.dto.GetNotificationResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationService {
    private final UserRepository userRepository;

    private final NotificationRepository notificationRepository;

    @Transactional
    public Long createNotification(CreateNotificationRequestDto createNotificationRequestDto){
        User sender = userRepository.findByUsername(createNotificationRequestDto.getSenderName()).get();
        User receiver = userRepository.findByUsername(createNotificationRequestDto.getReceiverName()).get();

        Notification notification = Notification.builder()
                .sender(sender)
                .receiver(receiver)
                .message(createNotificationRequestDto.getText())
                .read(false)
                .url(createNotificationRequestDto.getUrl())
                .build();

        return notificationRepository.save(notification).getId();
    }

    @Transactional
    public Long updateNotification(Long id){
        Notification notification = notificationRepository.findById(id).get();

        notification.setRead();

        return id;
    }

    @Transactional
    public List<GetNotificationResponseDto> getAllNotificationByUserIdReadFalse(Long userId){
        return notificationRepository.findByUserIdReadFalse(userId).stream()
                .map(GetNotificationResponseDto::new).collect(Collectors.toList());
    }
}
