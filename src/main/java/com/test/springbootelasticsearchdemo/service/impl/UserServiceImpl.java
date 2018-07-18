package com.test.springbootelasticsearchdemo.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import com.test.springbootelasticsearchdemo.entity.User;
import com.test.springbootelasticsearchdemo.repository.UserRepository;
import com.test.springbootelasticsearchdemo.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

/**
 * @author Ryan Miao at 2018-07-18 15:20
 **/
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
        ElasticsearchTemplate elasticsearchTemplate) {
        this.userRepository = userRepository;
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    @Override
    public Integer save(User user) {
        User saved = userRepository.save(user);
        return saved.getId();
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(
            list::add
        );
        return list;
    }

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(Integer id) {
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean bulkSave(List<User> users) {
        userRepository.saveAll(users);
        return true;
    }

    @Override
    public boolean templateSave(User user) {
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(user.getId()))
            .withObject(user).build();
        elasticsearchTemplate.index(indexQuery);
        elasticsearchTemplate.refresh(User.class);
        return true;
    }

    @Override
    public boolean templateBulkSave(List<User> users) {
        List<IndexQuery> indexQueries = users.stream()
            .map(user -> new IndexQueryBuilder().withId(String.valueOf(user.getId()))
                .withObject(user).build())
            .collect(Collectors.toList());
        elasticsearchTemplate.bulkIndex(indexQueries);
        elasticsearchTemplate.refresh(User.class);
        return true;
    }

    @Override
    public List<User> search(User user) {

//        SearchQuery searchQuery = new NativeSearchQueryBuilder()
//            .withQuery(matchAllQuery())
//            .withFilter(new BoolQueryBuilder().must(new MatchAllQueryBuilder().queryName("id").))
//            .build();
//
//        userRepository.search(searchQuery)
        return null;
    }

    @Override
    public List<User> ageRange(Integer start, Integer end) {
        return userRepository.getByAgeBetween(start, end);
    }

    @Override
    public Page<User> query(User user, Pageable pageable) {
        return userRepository.findByName(user.getName(), pageable);
    }
}
