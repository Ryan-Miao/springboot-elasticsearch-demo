package com.test.springbootelasticsearchdemo.repository;

import com.test.springbootelasticsearchdemo.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author Ryan Miao at 2018-07-18 14:54
 **/
public interface UserRepository extends ElasticsearchRepository<User, Integer> {

    List<User> getByAgeBetween(Integer start, Integer end);

    @Query(" {\"bool\" : {\"must\": {\"match\" : {\"name\" : \"?0\" }}}}")
    Page<User> findByName(String name, Pageable pageable);
}
