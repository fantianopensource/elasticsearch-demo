package com.example.elasticsearchdemo.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "articles")
public class ArticleDocument {
    @Id
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String userName;
}