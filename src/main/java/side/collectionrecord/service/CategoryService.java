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
import side.collectionrecord.web.dto.CategoryAddRequestDto;
import side.collectionrecord.web.dto.CategoryListResponseDto;
import side.collectionrecord.web.dto.CategoryUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    private final PostsRepository postsRepository;

    private final CommentRepository commentRepository;

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
    public List<CategoryListResponseDto> findCategories(Long userId){
        return categoryRepository.findAllCategory(userId).stream()
                .map(CategoryListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long id, CategoryUpdateRequestDto categoryUpdateRequestDto){
        Category category = categoryRepository.findById(id).orElse(null);
        category.update(categoryUpdateRequestDto.getName());

        return id;
    }

    @Transactional
    public void delete(Long id){
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
}
