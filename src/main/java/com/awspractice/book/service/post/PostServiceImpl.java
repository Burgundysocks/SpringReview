package com.awspractice.book.service.post;

import com.awspractice.book.domain.dto.PostDTO;
import com.awspractice.book.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    PostMapper pmapper;

    @Override
    public PostDTO regist(PostDTO pdto) {
        if (pmapper.regist(pdto)==1){
            return pdto;
        } else{
            return null;
        }
    }

    @Override
    public List<PostDTO> getPosts() {
        return pmapper.getPosts();
    }

    @Override
    public PostDTO getPostById(long postId) {
        return pmapper.getPostById(postId);
    }

    @Override
    public boolean updatePost(PostDTO pdto) {
        if (pmapper.updatePost(pdto)==1){
            return true;
        } else{
            return false;
        }
    }

    @Override
    public boolean deletePostById(long postId) {

        if (pmapper.deletePostById(postId)==1){
            return true;
        } else{
            return false;
        }
    }
}
