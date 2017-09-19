package com.mark.es.service;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.mark.es.model.User;

public interface UserSearchRepository extends ElasticsearchRepository<User, Long>{

}
