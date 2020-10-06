package com.example.myarticle.controller;

import com.example.myarticle.dto.CommentForm;
import com.example.myarticle.entity.Article;
import com.example.myarticle.entity.Comment;
import com.example.myarticle.repository.ArticleRepository;
import com.example.myarticle.repository.CommentRepository;
import com.example.myarticle.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    @PostMapping("/comments/{articleId}")
    public String create(CommentForm commentForm, @PathVariable Long articleId) {
        log.info(commentForm.toString());

        Comment comment = commentService.insert(commentForm.toEntity(), articleId);



//        Comment comment = commentForm.toEntity();
//        log.info(comment.toString());

        //질문
        //ManyToOne의 입장에서 작성
        //여러개의 댓글이 하나의 게시글이 달릴수 있다
//        Article article = articleRepository.findById(articleId).orElse(null);
//        log.info(article.toString());

        //댓글 여러개를 하나의 게시글에 연결시켜 준다
//        comment.stickTo(article);
//
//        Comment saved  = commentRepository.save(comment);
//        log.info(saved.toString());

        return "redirect:/articles/" + comment.getArticle().getId();
    }


    @PostMapping("/comments/{id}/update")
    public String update(CommentForm commentForm) {
        log.info("commentForm: " + commentForm.toString());

        Comment comment = commentRepository.findById(commentForm.getId()).orElse(null);

        comment.write(commentForm.getAuthor(), commentForm.getContent());

        Comment saved  = commentRepository.save(comment);
        log.info("saved:  " + saved.toString());
        return "redirect:/articles/" + saved.getArticle().getId();
    }
}
