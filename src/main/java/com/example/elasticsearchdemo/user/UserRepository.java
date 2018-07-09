package com.example.elasticsearchdemo.user;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface UserRepository extends ElasticsearchRepository<User, String> {

    public List<User> findByFirstName(String firstName);

    public List<User> findByLastName(String lastName);

}
