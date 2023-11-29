package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.GetSearchUserResponseDto;
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
    public List<GetSearchUserResponseDto> getAllUserByUsernameContains(String username, int page, int size){
        int offset = page * size;

        return userRepository.findByUsernameContains(username, offset, size).stream()
                .map(GetSearchUserResponseDto::new)
                .collect(Collectors.toList());
    }

//    private void validateDuplicateUser(String username){
//        Optional<User> findUser = userRepository.findByUsername(username);
//
//        if(findUser.isPresent()){
//            throw new CustomException(ErrorCode.USER_DUPLICATE);
//        }
//    }

    public boolean validateDuplicateUser(String username){
        Optional<User> findUser = userRepository.findByUsername(username);

        return findUser.isEmpty();
    }
}
