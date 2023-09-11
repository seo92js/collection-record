package side.collectionrecord.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;

    @Autowired
    CommentService commentService;

    @GetMapping("/posts/add")
    public String posts(Model model){
        Long userId = (Long) model.getAttribute("loginUserId");

        model.addAttribute("createPostsRequestDto", CreatePostsRequestDto.builder()
                        .userId(userId)
                        .category(null)
                        .artist(null)
                        .album(null)
                        .genre(null)
                        .albumArt(null)
                        .images(null)
                        .text(null)
                        .status(null)
                        .build());

        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());
        model.addAttribute("statuses", statuses);

        return "posts/postsAdd";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable Long id, Model model){

        Posts posts = postsService.getPostsById(id);

        model.addAttribute("username", posts.getUser().getUsername());

        model.addAttribute("getPostsResponseDto", new GetPostsResponseDto(posts));

        Long userId = (Long)model.getAttribute("loginUserId");

        if (posts.getUser().getId().equals(userId)){
            model.addAttribute("isMyPost", true);
        } else{
            model.addAttribute("isMyPost", false);
            model.addAttribute("userId", posts.getUser().getId());
        }

        model.addAttribute("createParentCommentRequestDto", CreateParentCommentRequestDto.builder()
                        .userId(userId)
                        .postsId(id)
                        .text(null)
                .build());

        model.addAttribute("createChildCommentRequestDto", CreateChildCommentRequestDto.builder()
                .userId(userId)
                .postsId(id)
                .parentCommentId(null)
                .text(null)
                .build());

        List<GetCommentResponseDto> parentComments = commentService.getAllParentCommentsByPosts(posts);
        model.addAttribute("parentComments", parentComments);

        List<GetCommentResponseDto> childComments = commentService.getAllChildCommentsByPosts(posts);
        model.addAttribute("childComments", childComments);

        return "posts/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        Posts posts = postsService.getPostsById(id);

        model.addAttribute("updatePostsRequestDto", UpdatePostsRequestDto.builder()
                        .category(posts.getCategory())
                        .artist(posts.getArtist())
                        .album(posts.getAlbum())
                        .genre(posts.getGenre())
                        .albumArt(posts.getAlbumArt())
                        .text(posts.getText())
                        .status(posts.getStatus())
                        .build());

        Long userId = (Long) model.getAttribute("loginUserId");

        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());
        model.addAttribute("statuses", statuses);

        // 일단은 첫번쨰 이미지만?
        model.addAttribute("imageId", posts.getImages().get(0).getId());

        model.addAttribute("postsId", id);

        return "posts/postsUpdate";
    }
}
