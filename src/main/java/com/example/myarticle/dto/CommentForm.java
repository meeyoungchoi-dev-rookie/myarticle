package com.example.myarticle.dto;

import com.example.myarticle.entity.Comment;
import lombok.Data;

@Data
public class CommentForm {

    private Long id;
    private String author;
    private String content;

    public Comment toEntity() {
        return Comment.builder()
                .content(content)
                .author(author)
                .id(id)
                .build();
    }


}
