package com.example.yourgeekengineer.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "blog_post")
@Getter
@Setter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private long blogId;

    @Column(name = "blog_name")
    private String blogName;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "last_updated_at")
    private Timestamp lastUpdatedAt;

    @Column(name = "published_at")
    private Timestamp publishedAt;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Category category;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "tag_blog_post",
            joinColumns = @JoinColumn(name = "blog_post_blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @Column(name = "likes")
    private int likes;

    @Column(name ="dislikes")
    private int dislikes;

    @Column(name="image_url")
    private String imageUrl;

    @Override
    public String toString() {
        return "BlogPost{" +
                "blogId=" + blogId +
                ", blogName='" + blogName + '\'' +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                ", author=" + author +
                ", categories=" + category+
                ", tags=" + tags +
                '}';
    }
}
