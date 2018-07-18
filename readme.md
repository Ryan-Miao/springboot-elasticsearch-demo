Springboot集成Elasticsearch学习
============================


## How to Run

```
# install
mvn clean install -s mvn-settings.xml

# run
mvn spring-boot:run  
or
Just run the com.test.springbootelasticsearchdemo.SpringbootElasticsearchDemoApplication.main

```

## problem

该版本的es不支持LocalDate等Java8新型API，只能自己覆盖写转换器。