package com.example.elasticsearchdemo.repository;

import com.example.elasticsearchdemo.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}