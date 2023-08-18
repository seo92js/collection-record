package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.image.ImageRepository;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.web.dto.CreatePostsRequestDto;
import side.collectionrecord.web.dto.GetArtistPostsResponseDto;
import side.collectionrecord.web.dto.GetCategoryPostsResponseDto;
import side.collectionrecord.web.dto.UpdatePostsRequestDto;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final UserRepository userRepository;

    private final PostsRepository postsRepository;

    private final ImageRepository imageRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public Posts getPostsById(Long id){
        return postsRepository.findById(id).get();
    }

    @Transactional
    public List<GetArtistPostsResponseDto> getAllPostsByArtist(Long userId, String artist, int page, int size){
        int offset = page * size;

        return postsRepository.findByUserIdAndArtist(userId, artist, offset, size).stream()
                .map(GetArtistPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<GetCategoryPostsResponseDto> getAllPostsByCategory(Long userId, String category, int page, int size){
        int offset = page * size;

        return postsRepository.findByUserIdAndCategory(userId, category, offset, size).stream()
                .map(GetCategoryPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<String> getAllArtistByUserId(Long userId){
        return postsRepository.findArtistByUserId(userId);
    }

    @Transactional
    public Long createPosts(CreatePostsRequestDto createPostsRequestDto){
        User user = userRepository.findById(createPostsRequestDto.getUserId()).orElse(null);

        return postsRepository.save(Posts.builder()
                        .user(user)
                        .category(createPostsRequestDto.getCategory())
                        .artist(createPostsRequestDto.getArtist())
                        .album(createPostsRequestDto.getAlbum())
                        .genre(createPostsRequestDto.getGenre())
                        .albumArt(createPostsRequestDto.getAlbumArt())
                        .representativeImage(createPostsRequestDto.getRepresentativeImage())
                        .text(createPostsRequestDto.getText())
                        .status(createPostsRequestDto.getStatus())
                        .build())
                        .getId();
    }

    @Transactional
    public Long updatePosts(Long userId, Long postsId, UpdatePostsRequestDto updatePostsRequestDto){
        Posts posts = postsRepository.findById(postsId).orElse(null);

        posts.update(updatePostsRequestDto.getCategory(),
                updatePostsRequestDto.getArtist(),
                updatePostsRequestDto.getAlbum(),
                updatePostsRequestDto.getGenre(),
                updatePostsRequestDto.getAlbumArt(),
                updatePostsRequestDto.getText(),
                updatePostsRequestDto.getStatus());

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
