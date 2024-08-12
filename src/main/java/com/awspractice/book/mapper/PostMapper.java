package com.awspractice.book.mapper;

import com.awspractice.book.domain.dto.PageDTO;
import com.awspractice.book.domain.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Mapper를 사용해서 특정 인터페이스를 매퍼로 등록
public interface PostMapper {
    //C
    int regist(PostDTO post);


    //R
    List<PostDTO> getPosts(PageDTO page);
    long getTotal();

    PostDTO getPostById(long postId);

    PostDTO getLastPost(Long userId);

    List<PostDTO> getPostsByUserId(Long userId);


    //U
    int updatePost(PostDTO post);


    //D
    int deletePostById(long postId);
}
