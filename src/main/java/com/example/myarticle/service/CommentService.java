package com.example.myarticle.service;

import com.example.myarticle.entity.Article;
import com.example.myarticle.entity.Comment;
import com.example.myarticle.repository.ArticleRepository;
import com.example.myarticle.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Slf4j
@Service
public class CommentService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Comment insert(Comment comment, @PathVariable Long articleId) {
        //질문 Many가 기준인건가?
        //ManyToOne
        //여러개의 댓글에 하나의 게시글이 연결된다
        //게시글 정보가 필요하다
        Article article   = articleRepository.findById(articleId).orElse(null);

        //여러개의 댓글에 하나의 게시글이 연결된다
        comment.stickTo(article);

        log.info(comment.toString());

        Comment saved = commentRepository.save(comment);
        log.info(saved.toString());

        return saved;
    }


}
