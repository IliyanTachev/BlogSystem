package core.blog.system.services.impl;

import core.blog.system.entities.Post;
import core.blog.system.models.binding.PostModel;
import core.blog.system.repositories.PostRepository;
import core.blog.system.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(PostModel post) {
        String formattedTags = String.join(",", post.getTags());
        post.setTags(formattedTags);
        post.setCreatedOn(LocalDate.now());
        this.postRepository.save(modelMapper.map(post, Post.class));
    }

    public PostModel getPostById(long postId){
        Post post = this.postRepository.findById(postId);
        return modelMapper.map(post, PostModel.class);
    }
}
