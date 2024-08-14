package com.awspractice.book.mapper;

import com.awspractice.book.domain.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int insertComment(CommentDTO comment);

    List<CommentDTO> getCommentsByPostId(Long postId);
    CommentDTO getCommentById(Long id);

    int updateComment(Long id, CommentDTO comment);

    int deleteComment(Long id);
}
