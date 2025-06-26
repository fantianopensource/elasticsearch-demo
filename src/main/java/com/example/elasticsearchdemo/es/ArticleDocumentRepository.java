package com.example.elasticsearchdemo.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleDocumentRepository extends ElasticsearchRepository<ArticleDocument, Long> {

    // Search by title or content
    Page<ArticleDocument> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    // Search by title
    Page<ArticleDocument> findByTitleContaining(String title, Pageable pageable);

    // Search by content
    Page<ArticleDocument> findByContentContaining(String content, Pageable pageable);
}