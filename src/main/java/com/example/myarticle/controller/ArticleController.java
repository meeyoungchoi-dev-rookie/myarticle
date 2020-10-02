package com.example.myarticle.controller;

import com.example.myarticle.dto.ArticleForm;
import com.example.myarticle.entity.Article;
import com.example.myarticle.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String index(Model model) {
        Iterable<Article> articles = articleRepository.findAll();
        model.addAttribute("articles", articles);
        return "articles/index";
    }

    @GetMapping("/articles/new")
    public String newArticle() {
        return "articles/new";
    }

    @PostMapping("/articles")
    public String create(ArticleForm articleForm) {
        log.info(articleForm.toString());

        Article saved = articleRepository.save(articleForm.toEntity());
        log.info("saved: " + saved.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{articleId}")
    public String show(@PathVariable Long articleId, Model model) {
        Article article = articleRepository.findById(articleId).orElse(null);

        if (article != null) {
            article.increase();
          article = articleRepository.save(article);
        }

        log.info(article.toString());

        model.addAttribute("article", article);
        return "articles/show";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String update(@PathVariable Long articleId, Model model) {
        Article article = articleRepository.findById(articleId).orElse(null);
        model.addAttribute("article", article);
        return "articles/update";
    }

    @PostMapping("/articles/update/{articleId}")
    public String update(ArticleForm articleForm, Model model, @PathVariable Long articleId){
        log.info(articleForm.toString());

        Article before = articleRepository.findById(articleId).orElse(null);
        log.info("before: " + before.toString());

        if (before != null) {
            before.rewrtie(articleForm.getTitle(), articleForm.getContent());
        }


        log.info(before.toString());

        Article saved = articleRepository.save(before);
        log.info(saved.toString());

        model.addAttribute("saved", saved);
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{articleId}/delete")
    public String delete(@PathVariable Long articleId) {
        articleRepository.deleteById(articleId);
        log.info(articleId + "번 글 삭제완료");
        return "redirect:/articles";
    }


}
