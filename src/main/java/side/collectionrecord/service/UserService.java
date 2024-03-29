package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.SearchUserResponseDto;
import side.collectionrecord.web.dto.UserProfileForm;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    @Transactional
    public Long userUpdate(Long id, UserProfileForm userProfileForm, Image image){
        User findUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        Image prevImage = findUser.getProfileImage();

        findUser.update(userProfileForm.getUsername(), image, userProfileForm.getProfileText());

        if (prevImage != null && prevImage.getId() != image.getId()){
            imageRepository.delete(prevImage);
        }

        return id;
    }

    @Transactional
    public UserProfileForm findById (Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        return UserProfileForm.builder()
                .username(user.getUsername())
                .profileText(user.getProfileText())
                .profileImage(null)
                .build();
    }

    @Transactional
    public List<SearchUserResponseDto> getAllUserByUsernameContains(String username, Pageable pageable){

        return userRepository.findByUsernameContains(username, pageable).stream()
                .map(SearchUserResponseDto::new)
                .collect(Collectors.toList());
    }

    public boolean validateDuplicateUser(Long id, String username){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        if (!user.getUsername().equals(username)){
            Optional<User> findUser = userRepository.findByUsername(username);
            return findUser.isEmpty();
        }

        return true;
    }
}
