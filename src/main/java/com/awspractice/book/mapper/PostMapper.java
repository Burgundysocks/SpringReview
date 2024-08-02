package com.awspractice.book.mapper;

import com.awspractice.book.domain.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
//@Mapper를 사용해서 특정 인터페이스를 매퍼로 등록
public interface PostMapper {
    //C
    int regist(PostDTO post);


    //R
    List<PostDTO> getPosts();

    PostDTO getPostById(long postId);


    //U
    int updatePost(PostDTO post);


    //D
    int deletePostById(long postId);
}
