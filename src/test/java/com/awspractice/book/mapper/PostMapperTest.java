package com.awspractice.book.mapper;

import com.awspractice.book.domain.dto.PostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    private PostDTO testPost;

    @BeforeEach
    //이걸 통해 다른 테스트를 실행할 때마다 주입
    void setUp() {
        testPost = new PostDTO();
        testPost.setPostId(1L);
        testPost.setPostTitle("Post Title");
        testPost.setPostContent("Post Content");
        testPost.setAuthor("abcd");
    }
    @Test
    void regist() {
        //given
        //setup메소드로 대체

        //when
        postMapper.regist(testPost);

        //then
        PostDTO retrievedPost = postMapper.getPostById(1L);
        assertNotNull(retrievedPost);
        assertEquals("Post Title", retrievedPost.getPostTitle());
        assertEquals("Post Content", retrievedPost.getPostContent());
        assertEquals("abcd", retrievedPost.getAuthor());
    }

    @Test
    void getPosts() {
        //given
        //setup
        postMapper.regist(testPost);

        //when
        List<PostDTO> retrievedPosts = postMapper.getPosts();

        //then
        assertNotNull(retrievedPosts);
        assertEquals(1, retrievedPosts.size()); //항상 초기화 되므로 크기는 1개여야 함
        assertEquals("Post Title", retrievedPosts.get(0).getPostTitle());
        assertEquals("Post Content", retrievedPosts.get(0).getPostContent());
        assertEquals("abcd", retrievedPosts.get(0).getAuthor());
    }

    @Test
    void getPostById() {
        //given
        postMapper.regist(testPost);
        //when
        PostDTO retrievedPost = postMapper.getPostById(1L);
        //then
        assertNotNull(retrievedPost);
        assertEquals("Post Title", retrievedPost.getPostTitle());
        assertEquals("Post Content", retrievedPost.getPostContent());
        assertEquals("abcd", retrievedPost.getAuthor());
    }

    @Test
    void updatePost() {
        //given
        postMapper.regist(testPost);
        //when
        PostDTO updatePost = postMapper.getPostById(1L);
        updatePost.setPostTitle("Updated Post Title");
        updatePost.setPostContent("Updated Post Content");
        updatePost.setAuthor("Updated Author");
        postMapper.updatePost(updatePost);
        //then
        PostDTO update = postMapper.getPostById(1L);
        //업데이트 되었기 때문에 testPost와 다른가에 대한 검사를 위해 NotEquals 사용
        assertNotEquals("Updated Post Title", testPost.getPostTitle());
        assertNotEquals("Updated Post Content", testPost.getPostContent());
        assertNotEquals("Updated Author", testPost.getAuthor());
    }

    @Test
    void deletePostById() {
        //given
        postMapper.regist(testPost);
        //when
        PostDTO retrievedPost = postMapper.getPostById(1L);
        postMapper.deletePostById(1L);

        //then
        assertNull(postMapper.getPostById(1L));
    }
}