package com.test.springbootelasticsearchdemo.controller;

import com.test.springbootelasticsearchdemo.entity.User;
import com.test.springbootelasticsearchdemo.service.UserService;
import io.swagger.annotations.Api;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ryan Miao at 2018-07-18 14:22
 **/
@Api
@RestController
@RequestMapping("/api/v1/users")
public class SampleController {

    private final UserService userService;

    @Autowired
    public SampleController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有.
     */
    @GetMapping("")
    public List<User> listUsers() {
        return userService.findAll();
    }

    /**
     * 根据id查询.
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
        return userService.findById(id);
    }

    /**
     * 使用repository存储.
     */
    @PostMapping("")
    public Integer saveUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    /**
     * 删除.
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return userService.deleteById(id);
    }

    /**
     * 批量添加索引.
     */
    @PostMapping("bulk-save")
    public boolean bulkSave(@RequestBody List<User> users) {
        return userService.bulkSave(users);
    }

    /**
     * 使用ElasticsearchTemplate进行单个索引.
     */
    @PostMapping("template-save")
    public boolean templateSave(@RequestBody User user) {
        return userService.templateSave(user);
    }

    /**
     * 使用ElasticsearchTemplate进行批量索引.
     */
    @PostMapping("template-bulk-save")
    public boolean templateBuldSave(@RequestBody List<User> users) {
        return userService.templateBulkSave(users);
    }

    /**
     * JPA语法使用.
     */
    @PostMapping("range-age")
    public List<User> ageRange(@RequestParam Integer start, @RequestParam Integer end) {
        return userService.ageRange(start, end);
    }

    /**
     * 使用Query 语法查询.分页查询name.
     */
    @PostMapping("query-lang")
    public Page<User> queryUser(@RequestBody User user, @RequestParam int pageSize,
        @RequestParam int pageNum) {
        return userService.query(user, PageRequest.of(pageNum, pageSize));
    }

    /**
     * template search.
     */
    @PostMapping("search")
    public List<User> conditionSearch(@RequestBody User user) {
        return userService.search(user);
    }

}
