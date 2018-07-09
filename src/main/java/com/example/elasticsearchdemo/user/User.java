package com.example.elasticsearchdemo.user;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "db_demo", type = "table_user", indexStoreType = "fs", shards = 5, replicas = 1, refreshInterval = "-1")
public class User {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String desc;


    public User() {
    }

    public User(String firstName, String lastName, String desc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.desc = desc;
    }

    public User(String id, String firstName, String lastName, String desc) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.desc = desc;
    }


}
