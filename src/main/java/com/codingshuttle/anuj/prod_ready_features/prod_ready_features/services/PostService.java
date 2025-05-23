package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();


    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

    PostDto updateNewPost(PostDto inputPost, Long postId);
}
