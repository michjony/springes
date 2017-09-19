package com.mark.es.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mark.es.model.Article;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long>{

}
