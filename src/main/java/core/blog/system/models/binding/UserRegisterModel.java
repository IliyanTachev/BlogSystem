package core.blog.system.models.binding;

import core.blog.system.entities.UserRole;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.Set;

@Component
public class UserRegisterModel {
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    private Set<UserRole> roles;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean passwordsMatch() {
        return this.password.equals(this.confirmPassword);
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }
}
