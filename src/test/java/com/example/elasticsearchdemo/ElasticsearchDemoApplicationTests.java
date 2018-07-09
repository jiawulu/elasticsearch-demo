package com.example.elasticsearchdemo;

import com.example.elasticsearchdemo.user.User;
import com.example.elasticsearchdemo.user.UserRepository;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.search.MultiMatchQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchDemoApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

//    @Before
//    public void before() {
//        esTemplate.deleteIndex(User.class);
//        esTemplate.createIndex(User.class);
//        esTemplate.putMapping(User.class);
//        esTemplate.refresh(User.class);
//    }

    @Test
    public void userSave() {


        Assert.assertNotNull(userRepository);

        User user = new User("1", "lu", "jiawu", "male 33 computer engineer");
        System.out.println(user);

        User save = userRepository.save(user);

        System.out.println(save);

        userRepository.save(new User("2", "yang", "jianfang", "female 30 officer"));

        Iterator<User> iterator = userRepository.findAll().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    @Test
    public void userRead() {

        List<User> lu = userRepository.findByFirstName("lu");

        for (User u : lu) {
            System.out.println(u);
        }

    }

    @Test
    public void userSearch() {

        FuzzyQueryBuilder desc = QueryBuilders.fuzzyQuery("desc", "30");

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(desc)
                .build();

        Page<User> search = userRepository.search(searchQuery);

        search.forEach( s -> {
            System.out.println(s);
        });


    }

}
