package com.awspractice.book.service.comment;

import com.awspractice.book.domain.dto.CommentDTO;
import com.awspractice.book.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper cmapper;


    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
       if (cmapper.insertComment(commentDTO)==1){
           return commentDTO;
       }else{
           return null;
       }
    }

    @Override
    public List<CommentDTO> getComments(Long postId) {
        return cmapper.getCommentsByPostId(postId);
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        return cmapper.getCommentById(id);
    }

    @Override
    public boolean updateComment(Long id, CommentDTO commentDTO) {
        if (cmapper.updateComment(id, commentDTO)==1){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean deleteComment(Long id) {
        if (cmapper.deleteComment(id)==1){
            return true;
        } else{
            return false;
        }
    }
}
