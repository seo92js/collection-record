package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsListResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public Posts findPosts(Long id){
        return postsRepository.findById(id).get();
    }

    @Transactional
    public List<PostsListResponseDto> findPostsList(Long userId, String categoryName){
        return postsRepository.findPostsList(userId, categoryName).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addPosts(PostsAddRequestDto postsAddRequestDto){

        Category category = categoryRepository.findByName(postsAddRequestDto.getCategoryName()).orElse(null);

        return postsRepository.save(Posts.builder()
                        .title(postsAddRequestDto.getTitle())
                        .image(postsAddRequestDto.getImage())
                        .text(postsAddRequestDto.getText())
                        .category(category)
                        .user(category.getUser())
                        .build())
                        .getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto postsUpdateRequestDto){
        Posts posts = postsRepository.findById(id).orElse(null);

        Category category = categoryRepository.findById(postsUpdateRequestDto.getCategoryId()).orElse(null);

        posts.update(category, postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getImage(), postsUpdateRequestDto.getText());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElse(null);

        postsRepository.delete(posts);
    }
}
