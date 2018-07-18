package com.test.springbootelasticsearchdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootElasticsearchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootElasticsearchDemoApplication.class, args);
    }

//    @Autowired
//    private CustomEntityMapper customEntityMapper;
//
//    @Bean
//    public ElasticsearchTemplate elasticsearchTemplate(TransportClient client,
//        ElasticsearchConverter converter) {
//        ElasticsearchTemplate elasticsearchTemplate = new ElasticsearchTemplate(client, converter,
//            customEntityMapper);
//        return elasticsearchTemplate;
//    }
}
