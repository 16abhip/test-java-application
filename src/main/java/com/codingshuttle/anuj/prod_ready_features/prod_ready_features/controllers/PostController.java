package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.controllers;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.PostDto;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPosts();
    }

    @GetMapping("/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto inputPost){
        return postService.createNewPost(inputPost);
    }

    @PutMapping("/{postId}")
    public PostDto updateNewPost(@RequestBody PostDto inputPost, @PathVariable Long postId){
        return postService.updateNewPost(inputPost, postId);
    }
}
