package com.example.elasticsearchdemo.controller;

import com.example.elasticsearchdemo.dto.ArticleCreateRequest;
import com.example.elasticsearchdemo.entity.Article;
import com.example.elasticsearchdemo.entity.User;
import com.example.elasticsearchdemo.es.ArticleDocument;
import com.example.elasticsearchdemo.es.ArticleDocumentRepository;
import com.example.elasticsearchdemo.service.ArticleService;
import com.example.elasticsearchdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleDocumentRepository articleDocumentRepository;

    @GetMapping
    public List<Article> list() {
        return articleService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Article> get(@PathVariable Long id) {
        return articleService.findById(id);
    }

    @PostMapping
    public Article create(@Valid @RequestBody ArticleCreateRequest request) {
        Article article = new Article();
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());

        if (request.getUserId() != null) {
            Optional<User> user = userService.findById(request.getUserId());
            if (user.isPresent()) {
                article.setUser(user.get());
            } else {
                throw new RuntimeException("User with id " + request.getUserId() + " not found");
            }
        }

        return articleService.save(article);
    }

    @PutMapping("/{id}")
    public Article update(@PathVariable Long id, @Valid @RequestBody ArticleCreateRequest request) {
        Optional<Article> existingArticle = articleService.findById(id);
        if (existingArticle.isPresent()) {
            Article article = existingArticle.get();
            article.setTitle(request.getTitle());
            article.setContent(request.getContent());

            if (request.getUserId() != null) {
                Optional<User> user = userService.findById(request.getUserId());
                if (user.isPresent()) {
                    article.setUser(user.get());
                } else {
                    throw new RuntimeException("User with id " + request.getUserId() + " not found");
                }
            }

            return articleService.save(article);
        } else {
            throw new RuntimeException("Article with id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        articleService.deleteById(id);
    }

    // Full-text search endpoint - using Repository simple queries
    @GetMapping("/search")
    public List<ArticleDocument> search(@RequestParam String keyword, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        // Use Repository's findBy methods for simple search
        Page<ArticleDocument> result = articleDocumentRepository.findByTitleContainingOrContentContaining(keyword,
                keyword, PageRequest.of(page, size));
        return result.getContent();
    }
}