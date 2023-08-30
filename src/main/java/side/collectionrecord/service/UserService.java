package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.exception.CustomException;
import side.collectionrecord.exception.ErrorCode;
import side.collectionrecord.web.dto.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final PasswordEncoder passwordEncoder;

    private final ResourceLoader resourceLoader;

    @Transactional
    public Long createUser(CreateUserRequestDto createUserRequestDto) throws IOException {

        validateDuplicateUser(createUserRequestDto.getUsername());

/*        File file = new File("./src/main/resources/static/img/default.jpg");

        Image image = Image.builder()
                .filename("default")
                .data(Files.readAllBytes(file.toPath()))
                .build();

        imageRepository.save(image);

        return userRepository.save(User.builder()
                .username(createUserRequestDto.getUsername())
                .password(createUserRequestDto.getPassword())
                .profileImage(image)
                .profileText(null)
                .userRole(UserRole.USER)
                .build()).getId();*/

        Resource resource = resourceLoader.getResource("classpath:static/img/default.jpg");

        try (InputStream inputStream = resource.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] imageData = outputStream.toByteArray();

            Image image = Image.builder()
                    .filename("default")
                    .data(imageData)
                    .build();

            imageRepository.save(image);

            return userRepository.save(User.builder()
                    .username(createUserRequestDto.getUsername())
                    .password(createUserRequestDto.getPassword())
                    .profileImage(image)
                    .profileText(null)
                    .userRole(UserRole.USER)
                    .build()).getId();
        }
    }

    @Transactional
    public Long updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        User findUser = userRepository.findById(id).orElse(null);

        Image prevImage = findUser.getProfileImage();

        findUser.update(updateUserRequestDto.getUsername(), updateUserRequestDto.getProfileImage(), updateUserRequestDto.getProfileText());

        if (prevImage != null && prevImage.getId() != updateUserRequestDto.getProfileImage().getId()){
            imageRepository.delete(prevImage);
        }

        return id;
    }

    @Transactional
    public Long updateUserPassword(Long id, UpdateUserPasswordRequestDto updateUserPasswordRequestDto){
        User findUser = userRepository.findById(id).orElse(null);

        validatePassword(updateUserPasswordRequestDto.getOldPassword(), findUser.getPassword());

        findUser.updatePassword(passwordEncoder.encode(updateUserPasswordRequestDto.getNewPassword()));

        return id;
    }

    @Transactional
    public GetUserProfileResponseDto getUserById (Long id){
        User user = userRepository.findById(id).orElse(null);

        return new GetUserProfileResponseDto(user);
    }

    @Transactional
    public List<GetSearchUserResponseDto> getAllUserByUsernameContains(String username, int page, int size){
        int offset = page * size;

        return userRepository.findByUsernameContains(username, offset, size).stream()
                .map(GetSearchUserResponseDto::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        if(findUser.isPresent()){
            throw new CustomException(ErrorCode.USER_DUPLICATE);
        }
    }

    private void validatePassword(String newPassword, String oldPassword){
        if (!passwordEncoder.matches(newPassword, oldPassword))
            throw new CustomException(ErrorCode.USER_DIFFERENT_PASSWORD);
    }
}
