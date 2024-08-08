package com.awspractice.book.service.post;

import com.awspractice.book.domain.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO regist(PostDTO pdto);
    List<PostDTO> getPosts();
    PostDTO getPostById(Long postId);
    PostDTO getLastPost(Long userId);
    boolean updatePost(Long postId, PostDTO pdto);
    boolean deletePostById(long postId);
}
