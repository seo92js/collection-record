package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.CustomException;
import side.collectionrecord.exception.ErrorCode;
import side.collectionrecord.web.dto.GetSearchUserResponseDto;
import side.collectionrecord.web.dto.GetUserProfileResponseDto;
import side.collectionrecord.web.dto.UpdateUserRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    @Transactional
    public Long updateUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        validateDuplicateUser(updateUserRequestDto.getUsername());

        User findUser = userRepository.findById(id).orElse(null);

        Image prevImage = findUser.getProfileImage();

        findUser.update(updateUserRequestDto.getUsername(), updateUserRequestDto.getProfileImage(), updateUserRequestDto.getProfileText());

        if (prevImage != null && prevImage.getId() != updateUserRequestDto.getProfileImage().getId()){
            imageRepository.delete(prevImage);
        }

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
}
