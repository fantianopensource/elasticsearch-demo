package com.example.elasticsearchdemo.service;

import com.example.elasticsearchdemo.entity.Article;
import com.example.elasticsearchdemo.entity.User;
import com.example.elasticsearchdemo.es.ArticleDocument;
import com.example.elasticsearchdemo.es.ArticleDocumentRepository;
import com.example.elasticsearchdemo.repository.ArticleRepository;
import com.example.elasticsearchdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleDocumentRepository articleDocumentRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Article save(Article article) {
        // Handle user association
        if (article.getUser() != null && article.getUser().getId() != null) {
            Optional<User> existingUser = userRepository.findById(article.getUser().getId());
            if (existingUser.isPresent()) {
                article.setUser(existingUser.get());
            } else {
                throw new RuntimeException("User with id " + article.getUser().getId() + " not found");
            }
        }

        Article savedArticle = articleRepository.save(article);

        // Sync to Elasticsearch
        ArticleDocument document = convertToDocument(savedArticle);
        articleDocumentRepository.save(document);

        return savedArticle;
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id) {
        // Check if article exists before deleting
        if (!articleRepository.existsById(id)) {
            throw new RuntimeException("Article with id " + id + " not found");
        }

        articleRepository.deleteById(id);
        articleDocumentRepository.deleteById(id);
    }

    private ArticleDocument convertToDocument(Article article) {
        ArticleDocument doc = new ArticleDocument();
        doc.setId(article.getId());
        doc.setTitle(article.getTitle());
        doc.setContent(article.getContent());
        User user = article.getUser();
        if (user != null) {
            doc.setUserId(user.getId());
            doc.setUserName(user.getName());
        }
        return doc;
    }
}