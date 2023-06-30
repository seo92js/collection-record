package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    @Transactional
    public Long join(UserJoinRequestDto userJoinRequestDto) throws IOException {

        validateDuplicateUser(userJoinRequestDto.getUsername());

        File file = new File("src/main/resources/static/img/default.jpg");

        Image image = Image.builder()
                .filename("default")
                .data(Files.readAllBytes(file.toPath()))
                .build();

        imageRepository.save(image);

        return userRepository.save(User.builder()
                .username(userJoinRequestDto.getUsername())
                .password(userJoinRequestDto.getPassword())
                .profileImage(image)
                .build()).getId();
    }

    @Transactional
    public Long update(Long id, UserUpdateRequestDto userUpdateRequestDto) throws IOException {
        User findUser = userRepository.findById(id).orElse(null);

        Image prevImage = findUser.getProfileImage();

        findUser.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getPassword(), userUpdateRequestDto.getProfileImage());

        if (prevImage.getId() != userUpdateRequestDto.getProfileImage().getId()){
            imageRepository.delete(prevImage);
        }

        return id;
    }

    @Transactional
    public UserProfileResponseDto findById (Long id){
        User user = userRepository.findById(id).orElse(null);

        return new UserProfileResponseDto(user);
    }

    @Transactional
    public List<UserSearchResponseDto> findContainsUsername(String username, int page, int size){
        int offset = page * size;

        return userRepository.findContainsUsername(username, offset, size).stream()
                .map(UserSearchResponseDto::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
