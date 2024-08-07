package com.awspractice.book.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
//기본생성자 자동추가 어노테이션
//public PostDTO(){}와 같은 효과
@Getter @Setter
//필드의 게터와 세터
public class PostDTO {
    private Long postId;
    private String postTitle;
    private String postContent;
    private String userId;
    private String CreateAt;
    private long likeCnt;
    private long viewCnt;
}
