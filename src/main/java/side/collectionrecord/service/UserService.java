package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.domain.user.UserRole;
import side.collectionrecord.web.dto.UserJoinRequestDto;
import side.collectionrecord.web.dto.UserProfileResponseDto;
import side.collectionrecord.web.dto.UserSearchResponseDto;
import side.collectionrecord.web.dto.UserUpdateRequestDto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    @Transactional
    public Long createUser(UserJoinRequestDto userJoinRequestDto) throws IOException {

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
                .profileText(null)
                .userRole(UserRole.USER)
                .build()).getId();
    }

    @Transactional
    public Long updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) throws IOException {
        User findUser = userRepository.findById(id).orElse(null);

        Image prevImage = findUser.getProfileImage();

        findUser.update(userUpdateRequestDto.getUsername(), userUpdateRequestDto.getProfileImage(), userUpdateRequestDto.getProfileText());

        if (prevImage != null && prevImage.getId() != userUpdateRequestDto.getProfileImage().getId()){
            imageRepository.delete(prevImage);
        }

        return id;
    }

    @Transactional
    public UserProfileResponseDto getUserById (Long id){
        User user = userRepository.findById(id).orElse(null);

        return new UserProfileResponseDto(user);
    }

    @Transactional
    public List<UserSearchResponseDto> getAllUserByUsernameContains(String username, int page, int size){
        int offset = page * size;

        return userRepository.findByUsernameContains(username, offset, size).stream()
                .map(UserSearchResponseDto::new)
                .collect(Collectors.toList());
    }

    private void validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        if(!findUser.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        //UserDetails 의 User 객체
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getUserRole().toString())
                .build();
    }
}
