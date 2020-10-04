package com.example.myarticle.service;

import com.example.myarticle.entity.Article;
import com.example.myarticle.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public Article insert(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public Article detail(@PathVariable Long id) {

        Article article = articleRepository.findById(id).orElse(null);

        if (article != null) {
            article.increase();
            article = articleRepository.save(article);
        }

        log.info(article.toString());

        return article;
    }


    @Transactional
    public Article edit(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    @Transactional
    public Article update(Article entity, @PathVariable Long articleId) {
        log.info("수정된 entity: " + entity.toString());
        Article article = articleRepository.findById(articleId).orElse(null);

        if (article != null) {
            article.rewrtie(entity.getTitle(), entity.getContent());
            log.info("복사후: " + article.toString());
        }
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return saved;
    }

    @Transactional
    public Iterable<Article> index() {
        return articleRepository.findAll();
    }


    @Transactional
    public void remove(@PathVariable Long articleId) {
        articleRepository.deleteById(articleId);
    }




}
