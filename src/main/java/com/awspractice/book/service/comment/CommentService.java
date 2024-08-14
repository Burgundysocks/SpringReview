package com.awspractice.book.service.comment;

import com.awspractice.book.domain.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO addComment(CommentDTO commentDTO);
    List<CommentDTO> getComments(Long id);
    CommentDTO getCommentById(Long id);
    boolean updateComment(Long id, CommentDTO commentDTO);
    boolean deleteComment(Long id);
}
