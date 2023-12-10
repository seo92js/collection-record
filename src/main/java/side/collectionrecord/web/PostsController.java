package side.collectionrecord.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import side.collectionrecord.config.auth.dto.SessionUser;
import side.collectionrecord.domain.category.Category;
import side.collectionrecord.domain.image.Image;
import side.collectionrecord.domain.posts.Posts;
import side.collectionrecord.domain.posts.PostsRepository;
import side.collectionrecord.domain.posts.PostsStatus;
import side.collectionrecord.exception.PostsNotFoundException;
import side.collectionrecord.service.CommentService;
import side.collectionrecord.service.ImageService;
import side.collectionrecord.service.PostsService;
import side.collectionrecord.web.dto.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostsController {
    private final PostsRepository postsRepository;
    private final PostsService postsService;
    private final ImageService imageService;
    private final CommentService commentService;

    @GetMapping("/posts/add")
    public String posts(Model model){

        List<Category> categories = Arrays.asList(Category.values());
        model.addAttribute("categories", categories);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());
        model.addAttribute("statuses", statuses);

        return "posts/postsAddForm";
    }

    @PostMapping("/posts/add")
    public String postsAdd(@ModelAttribute PostsAddForm postsAddForm, RedirectAttributes redirectAttributes) throws IOException {
        List<Long> imageIds = new ArrayList<>();

        for (MultipartFile image : postsAddForm.getImages()) {
            byte[] data = image.getBytes();
            Long imageId = imageService.createImage(ImageRequestDto.builder()
                    .filename(image.getOriginalFilename())
                    .data(data)
                    .build());

            imageIds.add(imageId);
        }

        List<Image> images = new ArrayList<>();

        for (Long imageId : imageIds){
            Image image = imageService.getImageById(imageId);
            images.add(image);
        }

        postsService.postsAdd(postsAddForm, images);

        redirectAttributes.addAttribute("userId", postsAddForm.getUserId());
        return "redirect:/user/{userId}/home";
    }

    @GetMapping("/posts/{id}")
    public String postsView(@PathVariable Long id, Model model, HttpSession session){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new PostsNotFoundException("게시물이 없습니다."));

        PostsResponseDto postsResponseDto = postsService.findById(id);

        model.addAttribute("userId", posts.getUser().getId());
        model.addAttribute("username", posts.getUser().getUsername());
        model.addAttribute("postsResponseDto", postsResponseDto);

        SessionUser user = (SessionUser) session.getAttribute("user");
        Long userId = user.getId();

        if (posts.getUser().getId().equals(userId)){
            model.addAttribute("isMyPost", true);
        } else{
            model.addAttribute("isMyPost", false);
            model.addAttribute("userId", posts.getUser().getId());
        }

        CommentParentForm commentParentForm = CommentParentForm.builder()
                .userId(userId)
                .postsId(id)
                .text(null)
                .build();

        model.addAttribute("commentParentForm", commentParentForm);

        CommentChildForm commentChildForm = CommentChildForm.builder()
                .userId(userId)
                .postsId(id)
                .parentCommentId(null)
                .text(null)
                .build();

        model.addAttribute("commentChildForm", commentChildForm);

        List<CommentResponseDto> parentComments = commentService.getAllParentCommentsByPosts(posts);
        model.addAttribute("parentComments", parentComments);

        List<CommentResponseDto> childComments = commentService.getAllChildCommentsByPosts(posts);
        model.addAttribute("childComments", childComments);

        return "posts/posts";
    }

    @GetMapping("/posts/{id}/update")
    public String postsUpdateForm(@PathVariable Long id, Model model){
        PostsResponseDto postsResponseDto = postsService.findById(id);

        model.addAttribute("postsResponseDto", postsResponseDto);

        List<PostsStatus> statuses = Arrays.asList(PostsStatus.values());
        model.addAttribute("statuses", statuses);

        return "posts/postsUpdateForm";
    }

    @PostMapping("/posts/{id}/update")
    public String postsUpdate(@PathVariable Long id, @ModelAttribute PostsUpdateForm postsUpdateForm, RedirectAttributes redirectAttributes){

        postsService.updatePosts(id, postsUpdateForm);

        redirectAttributes.addAttribute("postsId", id);
        return "redirect:/posts/{postsId}";
    }
}
