package com.codingshuttle.anuj.prod_ready_features.prod_ready_features.services;

import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.dto.PostDto;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.codingshuttle.anuj.prod_ready_features.prod_ready_features.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    @Override
    public PostDto updateNewPost(PostDto inputPost, Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id is invalid"));
        inputPost.setId(postId);
        modelMapper.map(inputPost, postEntity);
        return modelMapper.map(postRepository.save(postEntity), PostDto.class);
    }

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post id is invalid"));
        return modelMapper.map(postEntity, PostDto.class);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        postRepository.save(postEntity);
        return modelMapper.map(postEntity, PostDto.class);
    }
}
