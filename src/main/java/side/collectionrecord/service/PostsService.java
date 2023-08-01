package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.category.CategoryRepository;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.web.dto.PostsAddRequestDto;
import side.collectionrecord.web.dto.PostsListResponseDto;
import side.collectionrecord.web.dto.PostsSearchResponseDto;
import side.collectionrecord.web.dto.PostsUpdateRequestDto;

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
    public Posts findPosts(Long id){
        return postsRepository.findById(id).get();
    }

    @Transactional
    public List<PostsListResponseDto> findPostsList(Long userId, String categoryName, int page, int size){

        int offset = page * size;

        return postsRepository.findPostsList(userId, categoryName, offset, size).stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<PostsSearchResponseDto> findContainsHashtags(String hashtags, int page, int size){
        int offset = page * size;

        return postsRepository.findContainsHashtag(hashtags, offset, size).stream()
                .map(PostsSearchResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long addPosts(PostsAddRequestDto postsAddRequestDto){
        Category category = categoryRepository.findById(postsAddRequestDto.getCategoryId()).get();

        return postsRepository.save(Posts.builder()
                        .title(postsAddRequestDto.getTitle())
                        .representativeImage(postsAddRequestDto.getRepresentativeImage())
                        .text(postsAddRequestDto.getText())
                        .hashtags(postsAddRequestDto.getHashtags())
                        .category(category)
                        .user(category.getUser())
                        .status(postsAddRequestDto.getStatus())
                        .build())
                        .getId();
    }

    @Transactional
    public Long update(Long userId, Long postsId, PostsUpdateRequestDto postsUpdateRequestDto){
        Posts posts = postsRepository.findById(postsId).orElse(null);

        List<Image> prevImage = posts.getRepresentativeImage();

        Category category = categoryRepository.findById(postsUpdateRequestDto.getCategoryId()).get();

        posts.update(category, postsUpdateRequestDto.getTitle(), postsUpdateRequestDto.getRepresentativeImage(), postsUpdateRequestDto.getText(), postsUpdateRequestDto.getHashtags(), postsUpdateRequestDto.getStatus());

        if (prevImage != postsUpdateRequestDto.getRepresentativeImage()){
            for (Image image : prevImage){
                imageRepository.delete(image);
            }
        }

        return postsId;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElse(null);

        List<Comment> comments = posts.getComments();

        for (Comment comment : comments){
            commentRepository.delete(comment);
        }

        postsRepository.delete(posts);
    }
}
