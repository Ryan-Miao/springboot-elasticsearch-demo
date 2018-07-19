package com.test.springbootelasticsearchdemo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author Ryan Miao at 2018-07-18 14:23
 **/
@Data
@Document(indexName = "sys-user", type = "user")
public class User {

    @NotNull
    @Id
    private Integer id;
    @Field
    private String name;
    private Integer age;
    /**
     * 通过jackson映射字段名称.
     */
    @JsonProperty("birth_day")
    private Date birthDay;

    private List<Group> groups;

}
