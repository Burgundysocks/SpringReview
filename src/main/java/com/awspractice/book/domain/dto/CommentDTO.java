package com.awspractice.book.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDTO {
    private Long id;
    private Long postId;
    private Long parentId; // 대댓글의 부모 댓글 ID
    private String content;
    private String createdAt;
    private List<CommentDTO> children;
}
