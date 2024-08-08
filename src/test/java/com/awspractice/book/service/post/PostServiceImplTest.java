//package com.awspractice.book.service.post;
//
//import com.awspractice.book.domain.dto.PostDTO;
//import com.awspractice.book.mapper.PostMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.function.BooleanSupplier;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class PostServiceImplTest {
//
//    @Autowired
//    private PostService postService;
//
//    @Autowired
//    private PostMapper postMapper;
//
//    private PostDTO testPost;
//
//    @BeforeEach
//        //이걸 통해 다른 테스트를 실행할 때마다 주입
//    void setUp() {
//        testPost = new PostDTO();
//        testPost.setPostId(1L);
//        testPost.setPostTitle("Post Title");
//        testPost.setPostContent("Post Content");
//        testPost.setAuthor("abcd");
//    }
//
//    @Test
//    void regist() {
//        //given
//        //when
//        PostDTO result = this.postService.regist(testPost);
//
//        //then
//        if(result.getPostTitle() != null){
//            PostDTO createdPost = postMapper.getPostById(testPost.getPostId());
//            assertNotNull(createdPost);
//            assertEquals("Post Title", createdPost.getPostTitle());
//            assertEquals("Post Content", createdPost.getPostContent());
//            assertEquals("abcd", createdPost.getAuthor());
//        } else{
//            assertFalse(true);
//        }
//    }
//
//    @Test
//    void getPosts() {
//        //given
//        postService.regist(testPost);
//        //when
//        List<PostDTO> posts = postService.getPosts();
//        //then
//        assertNotNull(posts);
//        assertTrue(posts.size() > 0);
//        assertFalse(posts.isEmpty());
//    }
//
//    @Test
//    void getPostById() {
//        //given
//        postService.regist(testPost);
//        //when
//        PostDTO post = postService.getPostById(testPost.getPostId());
//        //then
//        assertNotNull(post);
//        assertEquals("Post Title", post.getPostTitle());
//        assertEquals("Post Content", post.getPostContent());
//        assertEquals("abcd", post.getAuthor());
//        assertFalse(0 == post.getPostId());
//    }
//
//    @Test
//    void updatePost() {
//        //given
//        postService.regist(testPost);
//        //when
//        PostDTO post = postService.getPostById(testPost.getPostId());
//        Long postId = post.getPostId();
//        post.setPostTitle("New Post Title");
//        post.setPostContent("New Post Content");
//        boolean result = this.postService.updatePost(postId, post);
//        //then
//        assertTrue(result);
//        assertEquals("New Post Title", post.getPostTitle());
//        assertEquals("New Post Content", post.getPostContent());
//
//
//    }
//
//    @Test
//    void deletePostById() {
//        //given
//        postService.regist(testPost);
//        //when
//        boolean result = this.postService.deletePostById(testPost.getPostId());
//
//        //then
//        if(result){
//            assertTrue(result);
//            assertNull(postMapper.getPostById(testPost.getPostId()));
//        } else{
//            assertFalse(true);
//        }
//    }
//}