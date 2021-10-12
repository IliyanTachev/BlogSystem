package core.blog.system.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="posts")
public class Post {
    private long id;
    private String title;
    private String description;
    private String author;
    private String tags;
    private LocalDate createdOn;
    private int likesNumber;
    private int dislikesNumber;
    private long[] likedByUserIDs;
    private long[] dislikedByUserIDs;

    public Post() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Column(name="created_on")
    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Column(name="likes_num")
    public int getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(int likesNumber) {
        this.likesNumber = likesNumber;
    }

    @Column(name="dislikes_num")
    public int getDislikesNumber() {
        return dislikesNumber;
    }

    public void setDislikesNumber(int dislikesNumber) {
        this.dislikesNumber = dislikesNumber;
    }
}
