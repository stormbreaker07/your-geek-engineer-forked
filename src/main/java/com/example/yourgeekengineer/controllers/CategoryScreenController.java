package com.example.yourgeekengineer.controllers;


import com.example.yourgeekengineer.entities.BlogPost;
import com.example.yourgeekengineer.models.RequestCategoryModel;
import com.example.yourgeekengineer.models.RequestTagModel;
import com.example.yourgeekengineer.models.SearchBlogPostInfo;
import com.example.yourgeekengineer.services.CategoryScreenService;
import com.example.yourgeekengineer.services.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryScreenController {

    @Autowired
    private TagsService tagsService;
    @Autowired
    private CategoryScreenService categoryScreenService;

    @PostMapping("/info")
    public ResponseEntity<Map<String, Object>> getBlogsScreenInfo(@RequestBody RequestCategoryModel category) throws Exception {
        try {
            return ResponseEntity.ok().body(categoryScreenService.getAllScreenData(category));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @PostMapping("/tag")
    public ResponseEntity<List<BlogPost>> getBlogsByTagName(@RequestBody RequestTagModel requestModel) throws Exception{
        try {
            return ResponseEntity.ok().body(tagsService.getAllBlogByTagName(requestModel));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all-blogs-info/{category-name}")
    public ResponseEntity<List<SearchBlogPostInfo>> getAllBlogsInfo(@PathVariable("category-name") String categoryName) throws Exception {
        try {
            return ResponseEntity.ok().body(categoryScreenService.getAllBlogsInfo(categoryName));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

}
