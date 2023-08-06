package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.web.dto.CreatePostsRequestDto;
import side.collectionrecord.web.dto.GetCategoryPostsResponseDto;
import side.collectionrecord.web.dto.GetSearchPostsResponseDto;
import side.collectionrecord.web.dto.UpdatePostsRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    private final CategoryRepository categoryRepository;

    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public Posts getPostsById(Long id){
        return postsRepository.findById(id).get();
    }

    @Transactional
    public List<GetCategoryPostsResponseDto> getAllPostsByCategoryName(Long userId, String categoryName, int page, int size){

        int offset = page * size;

        return postsRepository.findByUserIdAndCategory(userId, categoryName, offset, size).stream()
                .map(GetCategoryPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetSearchPostsResponseDto> getAllPostsByHashtagsContains(String hashtags, int page, int size){
        int offset = page * size;

        return postsRepository.findByHashtagContains(hashtags, offset, size).stream()
                .map(GetSearchPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long createPosts(CreatePostsRequestDto createPostsRequestDto){
        Category category = categoryRepository.findById(createPostsRequestDto.getCategoryId()).get();

        return postsRepository.save(Posts.builder()
                        .title(createPostsRequestDto.getTitle())
                        .representativeImage(createPostsRequestDto.getRepresentativeImage())
                        .text(createPostsRequestDto.getText())
                        .hashtags(createPostsRequestDto.getHashtags())
                        .category(category)
                        .user(category.getUser())
                        .status(createPostsRequestDto.getStatus())
                        .build())
                        .getId();
    }

    @Transactional
    public Long updatePosts(Long userId, Long postsId, UpdatePostsRequestDto updatePostsRequestDto){
        Posts posts = postsRepository.findById(postsId).orElse(null);

        Category category = categoryRepository.findById(updatePostsRequestDto.getCategoryId()).get();

        posts.update(category, updatePostsRequestDto.getTitle(), updatePostsRequestDto.getText(), updatePostsRequestDto.getHashtags(), updatePostsRequestDto.getStatus());

        return postsId;
    }

    @Transactional
    public void deletePosts(Long id){
        Posts posts = postsRepository.findById(id).orElse(null);

        List<Comment> comments = posts.getComments();

        for (Comment comment : comments){
            commentRepository.delete(comment);
        }

        postsRepository.delete(posts);
    }
}
