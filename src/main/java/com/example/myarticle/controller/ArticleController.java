package com.example.myarticle.controller;

import com.example.myarticle.dto.ArticleForm;
import com.example.myarticle.entity.Article;
import com.example.myarticle.repository.ArticleRepository;
import com.example.myarticle.service.ArticleService;
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
    private ArticleService articleService;

    @GetMapping("/articles")
    public String index(Model model) {
        Iterable<Article> articles = articleService.index();
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

        Article saved = articleService.insert(articleForm.toEntity());
        log.info("saved: " + saved.toString());
        return "redirect:/articles";
    }

    @GetMapping("/articles/{articleId}")
    public String show(@PathVariable Long articleId, Model model) {
        Article article = articleService.detail(articleId);

        log.info(article.toString());

        model.addAttribute("article", article);
        model.addAttribute("comments", article.getComments());



        return "articles/show";
    }

    @GetMapping("/articles/{articleId}/edit")
    public String update(@PathVariable Long articleId, Model model) {
        Article article = articleService.edit(articleId);
        model.addAttribute("article", article);
        return "articles/update";
    }

    @PostMapping("/articles/update/{articleId}")
    public String update(ArticleForm articleForm, Model model, @PathVariable Long articleId) {
        Article saved = articleService.update(articleForm.toEntity(), articleId);

        log.info(saved.toString());
        model.addAttribute("saved", saved);
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{articleId}/delete")
    public String delete(@PathVariable Long articleId) {
        articleService.remove(articleId);
        log.info(articleId + "번 글 삭제완료");
        return "redirect:/articles";
    }


}
