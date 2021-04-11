package core.blog.system.models.binding;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PostModel {
    private String title;
    private String description;
    private String author;
    private String tags;
    private LocalDate createdOn;

    public PostModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String[] getTags() {
        return tags.split(" ");
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }
}
