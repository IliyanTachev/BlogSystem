package core.blog.system.controllers;

import core.blog.system.entities.Post;
import core.blog.system.entities.User;
import core.blog.system.models.binding.PostModel;
import core.blog.system.models.binding.UserModel;
import core.blog.system.services.PostService;
import core.blog.system.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class PostController {
    private PostService postService;
    private UserService userService;
    private ModelMapper modelMapper;

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
        userService.save(modelMapper.map(user, User.class));
        return "success";
    }

    @GetMapping("/posts/view/{id}")
    public String viewPost(@PathVariable long id, Model model){
        PostModel fetchedPost = this.postService.getPostById(id);
        model.addAttribute("post", fetchedPost);
        String[] tags = fetchedPost.getTags();
        return "post_view";
    }

}
