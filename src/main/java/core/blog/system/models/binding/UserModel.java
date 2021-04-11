package core.blog.system.models.binding;

import core.blog.system.entities.Post;
import core.blog.system.entities.UserRole;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserModel {
    private String email;
    private String username;
    private String password;
    private Set<UserRole> roles;
    private Set<Post> posts;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}
