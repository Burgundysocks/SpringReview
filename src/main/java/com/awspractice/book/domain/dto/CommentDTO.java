package com.awspractice.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private String content;
    private String createdAt;
}
