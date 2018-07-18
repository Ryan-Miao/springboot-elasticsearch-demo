package com.test.springbootelasticsearchdemo.service;

import com.test.springbootelasticsearchdemo.entity.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Ryan Miao at 2018-07-18 15:18
 **/
public interface UserService {

    Integer save(User user);
    List<User> findAll();
    User findById(Integer id);
    boolean deleteById(Integer id);

    boolean bulkSave(List<User> users);

    boolean templateSave(User user);

    boolean templateBulkSave(List<User> users);

    List<User> search(User user);

    List<User> ageRange(Integer start, Integer end);

    Page<User> query(User user,  Pageable pageable);
}
