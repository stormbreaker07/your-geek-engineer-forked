package com.example.yourgeekengineer.services;

import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.entities.Category;
import com.example.yourgeekengineer.entities.Tag;
import com.example.yourgeekengineer.models.BlogPostModal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RefactorNewBlogPostService {
    private static final Logger logger = Logger.getLogger(RefactorNewBlogPostService.class.getName());

    @Autowired
    private TagsService tagsService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private AuthorService authorService;
    public BlogPost refactorBlogPostModalToEntity(BlogPostModal blogPostModal) throws Exception {
        BlogPost blogPost = new BlogPost();
        // get all required tags
        List<Tag> requiredTags = tagsService.addNewTags(stringToList(blogPostModal.getTags()));
        blogPost.setTags(requiredTags);
        logger.info("tags are successfully retrieved");
        //get required category
        try {
            Category category = categoryService.getCategoryByCategoryName(blogPostModal.getCategory());
            blogPost.getCategories().add(category);
        } catch (Exception e) {
            logger.warning(e.getMessage());
            throw new RuntimeException(e);
        }
        //set blog name
        blogPost.setBlogName(blogPostModal.getBlogName());
        //set content
        blogPost.setContent(blogPostModal.getBlogContent());
        //add author
        try {
            blogPost.setAuthor(authorService.getAuthorByUserId(blogPostModal.getUserId()));
        } catch(Exception e) {
            logger.warning(e.getMessage());
            throw new Exception(e.getMessage());
        }

        //set created at and published at
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        blogPost.setCreatedAt(currentTimestamp);
        blogPost.setPublishedAt(currentTimestamp);
        logger.info("blog post" + blogPost.getBlogName() + "published at " + blogPost.getPublishedAt());
        return blogPost;
    }

    private List<String> stringToList(String tagName) {
        return Arrays.asList(tagName.split(","));
    }

}
