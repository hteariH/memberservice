package com.mamoru.memberservice.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * user: alekseyb
 * date: 04.07.18
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;
    @Value("${spring.data.mongodb.uri}")
    private String mongoURI;

    @Bean
    public GridFsTemplate gridFsTemplate() throws Exception {
        return new GridFsTemplate(mongoDbFactory(),mappingMongoConverter());
    }


    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(new MongoClientURI(mongoURI));
    }



}
