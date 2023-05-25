package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.web.dto.PostsAddRequestDto;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long addPosts(PostsAddRequestDto postsAddRequestDto){

        Category category = categoryRepository.findById(postsAddRequestDto.getCategoryId()).orElse(null);

        return postsRepository.save(Posts.builder()
                        .title(postsAddRequestDto.getTitle())
                        .image(postsAddRequestDto.getImage())
                        .text(postsAddRequestDto.getText())
                        .category(category)
                        .build())
                        .getId();
    }
}
