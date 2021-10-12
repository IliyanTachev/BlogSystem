package core.blog.system.controllers;

import core.blog.system.entities.Post;
import core.blog.system.entities.User;
import core.blog.system.models.binding.PostModel;
import core.blog.system.services.PostService;
import core.blog.system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class PostController {
    private final PostService postService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public PostController(PostService postService, UserService userService, ModelMapper modelMapper) {
        this.postService = postService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost(@ModelAttribute PostModel post){
        User user = userService.getUserByUsername(post.getAuthor());
        user.getPosts().add(modelMapper.map(post, Post.class));
        userService.save(user);
        return "success";
    }

    @GetMapping("/posts/view/{id}")
    public String viewPost(@PathVariable long id, Model model){
        PostModel fetchedPost = this.postService.getPostById(id);
        model.addAttribute("post", fetchedPost);
        return "post_view";
    }

    @PostMapping("/posts/update/rating/likes/{id}")
    @ResponseBody
    public int[] updateLikes(@PathVariable long id){
        PostModel post = this.postService.getPostById(id);
        int updatedLikesNumber = post.getLikesNumber() + 1;
        int updatedDislikesNumber = post.getDislikesNumber() - 1;
        post.setLikesNumber(updatedLikesNumber);
        post.setDislikesNumber(updatedDislikesNumber);
        this.postService.save(post);
        return new int[]{updatedLikesNumber, updatedDislikesNumber};
    }

    @PostMapping("/posts/update/rating/dislikes/{id}")
    @ResponseBody
    public int[] updateDislikes(@PathVariable long id){
        PostModel post = this.postService.getPostById(id);
        int updatedDislikesNumber = post.getDislikesNumber() + 1;
        int updatedLikesNumber = post.getLikesNumber() - 1;
        post.setDislikesNumber(updatedDislikesNumber);
        post.setLikesNumber(updatedLikesNumber);
        this.postService.save(post);
        return new int[]{updatedDislikesNumber, updatedLikesNumber};
    }
}
