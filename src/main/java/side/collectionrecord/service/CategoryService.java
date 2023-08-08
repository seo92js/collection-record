package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.CustomException;
import side.collectionrecord.exception.ErrorCode;
import side.collectionrecord.web.dto.CreateChildCategoryRequestDto;
import side.collectionrecord.web.dto.CreateParentCategoryRequestDto;
import side.collectionrecord.web.dto.GetCategoryResponseDto;
import side.collectionrecord.web.dto.UpdateCategoryRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final PostsRepository postsRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public Long createParentCategory(CreateParentCategoryRequestDto createParentCategoryRequestDto){
        Long userId = createParentCategoryRequestDto.getUserId();

        validateDuplicateParentCategory(userId, createParentCategoryRequestDto.getName());

        User findUser = userRepository.findById(userId).orElse(null);

        Category category = Category.builder()
                .user(findUser)
                .parentCategory(null)
                .name(createParentCategoryRequestDto.getName())
                .build();

        categoryRepository.save(category);

        return category.getId();
    }

    @Transactional
    public Long createChildCategory(CreateChildCategoryRequestDto createChildCategoryRequestDto){
        Long userId = createChildCategoryRequestDto.getUserId();

        validateDuplicateChildCategory(userId, createChildCategoryRequestDto.getName());

        User findUser = userRepository.findById(userId).orElse(null);

        Category parentCategory = categoryRepository.findById(createChildCategoryRequestDto.getParentCategoryId()).get();

        Category category = Category.builder()
                .user(findUser)
                .parentCategory(parentCategory)
                .name(createChildCategoryRequestDto.getName())
                .build();

        categoryRepository.save(category);

        return category.getId();
    }

    @Transactional
    public List<GetCategoryResponseDto> getAllParentCategoryByUserId(Long userId){
        return categoryRepository.findAllParentCategory(userId).stream()
                .map(GetCategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetCategoryResponseDto> getAllChildCategoryByUserId(Long userId){
        return categoryRepository.findAllChildCategory(userId).stream()
                .map(GetCategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long updateCategory(Long id, UpdateCategoryRequestDto updateCategoryRequestDto){
        Category category = categoryRepository.findById(id).orElse(null);
        category.update(updateCategoryRequestDto.getName());

        return id;
    }

    @Transactional
    public void deleteCategory(Long id){
        Category category = categoryRepository.findById(id).orElse(null);

        List<Posts> posts = category.getPosts();

        for (Posts post : posts){
            List<Comment> comments = post.getComments();

            for (Comment comment : comments){
                commentRepository.delete(comment);
            }
            postsRepository.delete(post);
        }

        categoryRepository.delete(category);
    }

    private void validateDuplicateParentCategory(Long userId, String parentCategoryName){
        Optional<Category> category = categoryRepository.findByName(userId, parentCategoryName);

        if(category.isPresent()){
            throw new CustomException(ErrorCode.CATEGORY_DUPLICATE);
        }
    }

    private void validateDuplicateChildCategory(Long userId, String childCategoryName){
        List<Category> allChildCategory = categoryRepository.findAllChildCategory(userId);

        for (Category category : allChildCategory){
            if (category.getName().equals(childCategoryName))
                throw new CustomException(ErrorCode.CATEGORY_DUPLICATE);
        }
    }
}
