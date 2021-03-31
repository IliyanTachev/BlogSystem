package core.blog.system.models.binding;

import core.blog.system.entities.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserPostsModel {
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
