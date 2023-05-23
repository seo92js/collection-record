package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long addCategory(CategoryAddRequestDto categoryAddRequestDto){
        Long userId = categoryAddRequestDto.getUserId();

        User findUser = userRepository.findById(userId).orElse(null);

        Category category = Category.builder()
                .user(findUser)
                .name(categoryAddRequestDto.getName())
                .build();

        categoryRepository.save(category);

        return category.getId();
    }

    @Transactional
    public List<CategoryListResponseDto> findCategories(User user){
        return categoryRepository.findAllCategory(user).stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
