package com.example.myarticle.dto;

import com.example.myarticle.entity.Article;

public class ArticleForm {

    private String title;
    private String content;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleForm(String title, String content) {
        this.title = title;
        this.content = content;
    }



    public Article toEntity() {
        return Article.builder()
                .content(content)
                .title(title)
                .build();
    }

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

}
