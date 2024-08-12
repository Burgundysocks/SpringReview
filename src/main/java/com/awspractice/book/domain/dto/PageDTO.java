package com.awspractice.book.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
public class PageDTO {
    private int page;
    private int pageSize;
    private int startRow;
    private String keyword;

    public PageDTO() {
        this(1,10);
    }

    public PageDTO(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
        this.startRow = (this.page - 1) * this.pageSize;
    }

    public void setPage(int page) {
        this.page = page;
        this.startRow = (this.page - 1) * this.pageSize;
    }

    public String getListLink() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("keyword", keyword);
        return builder.toUriString();
    }
}
