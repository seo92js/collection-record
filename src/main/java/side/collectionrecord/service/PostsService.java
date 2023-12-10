package side.collectionrecord.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import side.collectionrecord.domain.comment.Comment;
import side.collectionrecord.domain.comment.CommentRepository;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.user.User;
import side.collectionrecord.domain.user.UserRepository;
import side.collectionrecord.exception.PostsNotFoundException;
import side.collectionrecord.exception.UserNotFoundException;
import side.collectionrecord.web.dto.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final UserRepository userRepository;

    private final PostsRepository postsRepository;

    private final CommentRepository commentRepository;

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        return new PostsResponseDto(posts);
    }

    @Transactional
    public List<ArtistPostsResponseDto> getAllPostsByUserIdAndArtist(Long userId, String artist, Pageable pageable){

        return postsRepository.findByUserIdAndArtist(userId, artist, pageable).stream()
                .map(ArtistPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SearchArtistPostsResponseDto> getAllPostsByArtistContains(String artist, Pageable pageable){

        return postsRepository.findByArtistContains(artist, pageable).stream()
                .map(SearchArtistPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CategoryPostsResponseDto> getAllPostsByUserIdAndCategory(Long userId, String category, Pageable pageable){

        return postsRepository.findByUserIdAndCategory(userId, category, pageable).stream()
                .map(CategoryPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SearchAlbumPostsResponseDto> getAllPostsByAlbumContains(String album, Pageable pageable){

        return postsRepository.findByAlbumContains(album, pageable).stream()
                .map(SearchAlbumPostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<String> getAllArtistByUserId(Long userId){
        return postsRepository.findArtistByUserId(userId);
    }

    @Transactional
    public Long postsAdd(PostsAddForm postsAddForm, List<Image> images) {
        User user = userRepository.findById(postsAddForm.getUserId()).orElseThrow(() -> new UserNotFoundException("유저가 없습니다."));

        return postsRepository.save(Posts.builder()
                        .user(user)
                        .category(postsAddForm.getCategory())
                        .artist(postsAddForm.getArtist())
                        .album(postsAddForm.getAlbum())
                        .genre(postsAddForm.getGenre())
                        .albumArt(postsAddForm.getAlbumArt())
                        .images(images)
                        .text(postsAddForm.getText())
                        .status(postsAddForm.getStatus())
                        .build())
                        .getId();
    }

    @Transactional
    public Long updatePosts(Long postsId, PostsUpdateForm postsUpdateForm){
        Posts posts = postsRepository.findById(postsId).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        posts.update(postsUpdateForm.getText(), postsUpdateForm.getStatus());

        return postsId;
    }

    @Transactional
    public void deletePosts(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        List<Comment> comments = posts.getComments();

        for (Comment comment : comments){
            commentRepository.delete(comment);
        }

        postsRepository.delete(posts);
    }
}
