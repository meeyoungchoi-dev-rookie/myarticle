package com.example.myarticle.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Builder
public class Comment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @JoinColumn(name = "articleId")
    @ManyToOne
    private Article article;


    public void stickTo(Article article) {
        this.article = article;
    }

    public void write(String author, String content) {
        this.author = author;
        this.content = content;
    }
}
