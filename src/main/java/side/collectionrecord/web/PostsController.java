package side.collectionrecord.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.service.CategoryService;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PostsController {
    @Autowired
    PostsService postsService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    CommentService commentService;

    @GetMapping("/posts/add")
    public String posts(Model model){
        Long userId = (Long) model.getAttribute("loginUserId");

        List<CategoryListResponseDto> categories = categoryService.findCategories(userId);

        model.addAttribute("postsAddRequestDto", PostsAddRequestDto.builder()
                        .userId(userId)
                        .categoryName(null)
                        .title(null)
                        .representativeImage(null)
                        .text(null)
                        .hashtags(null)
                        .status(null)
                        .build());

        model.addAttribute("categories", categories);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());

        model.addAttribute("statuses", statuses);

        return "posts/postsAdd";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable Long id, Model model){

        Posts posts = postsService.findPosts(id);

        model.addAttribute("username", posts.getUser().getUsername());

        model.addAttribute("postsResponseDto", new PostsResponseDto(posts));

        Long userId = (Long)model.getAttribute("loginUserId");

        model.addAttribute("commentAddRequestDto", CommentAddRequestDto.builder()
                        .userId(userId)
                        .postsId(id)
                        .text(null)
                .build());

        List<CommentListResponseDto> comments = commentService.findComments(posts);

        model.addAttribute("comments", comments);

        return "posts/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){

        Posts posts = postsService.findPosts(id);

        model.addAttribute(PostsUpdateRequestDto.builder()
                        .categoryName(posts.getCategory().getName())
                        .title(posts.getTitle())
                        .text(posts.getText())
                        .representativeImage(posts.getRepresentativeImage())
                        .hashtags(posts.getHashtags())
                        .status(posts.getStatus())
                        .build());

        Long userId = (Long) model.getAttribute("loginUserId");

        model.addAttribute("loginUsername", model.getAttribute("loginUsername"));

        List<CategoryListResponseDto> categories = categoryService.findCategories(userId);

        model.addAttribute("categories", categories);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());

        model.addAttribute("statuses", statuses);

        model.addAttribute("imageId", posts.getRepresentativeImage().getId());

        model.addAttribute("postsId", id);

        return "posts/postsUpdate";
    }
}
