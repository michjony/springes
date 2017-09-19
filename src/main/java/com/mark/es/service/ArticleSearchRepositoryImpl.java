package com.mark.es.service;

import java.util.Optional;

import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.mark.es.model.Article;

@Service
public class ArticleSearchRepositoryImpl implements ArticleSearchRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleSearchRepositoryImpl.class);

	@Override
	public <S extends Article> S index(S entity) {
		LOGGER.info("entity : " + entity);
		return null;
	}

	@Override
	public Iterable<Article> search(QueryBuilder query) {
		return null;
	}

	@Override
	public Page<Article> search(QueryBuilder query, Pageable pageable) {
		return null;
	}

	@Override
	public Page<Article> search(SearchQuery searchQuery) {
		return null;
	}

	@Override
	public Page<Article> searchSimilar(Article entity, String[] fields, Pageable pageable) {
		return null;
	}

	@Override
	public void refresh() {

	}

	@Override
	public Class<Article> getEntityClass() {
		return null;
	}

	@Override
	public Iterable<Article> findAll(Sort arg0) {
		return null;
	}

	@Override
	public Page<Article> findAll(Pageable arg0) {
		return null;
	}

	@Override
	public long count() {
		return 0;
	}

	@Override
	public void delete(Article arg0) {

	}

	@Override
	public void deleteAll() {

	}

	@Override
	public void deleteAll(Iterable<? extends Article> arg0) {

	}

	@Override
	public void deleteById(Long arg0) {

	}

	@Override
	public boolean existsById(Long arg0) {
		return false;
	}

	@Override
	public Iterable<Article> findAll() {
		return null;
	}

	@Override
	public Iterable<Article> findAllById(Iterable<Long> arg0) {
		return null;
	}

	@Override
	public Optional<Article> findById(Long arg0) {
		return null;
	}

	@Override
	public <S extends Article> S save(S arg0) {
		return null;
	}

	@Override
	public <S extends Article> Iterable<S> saveAll(Iterable<S> arg0) {
		return null;
	}

}
