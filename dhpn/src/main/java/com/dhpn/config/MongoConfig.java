package com.dhpn.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Configuration
@EnableMongoRepositories("com.dhpn.repositories")
public class MongoConfig extends AbstractMongoConfiguration{

    private static final String DB_NAME = "dhnp";
    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;

    @Override
    protected String getDatabaseName() {
        return DB_NAME;
    }

    @Override
    public Mongo mongo() throws Exception {

        MongoClient mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);

        mongoClient.setWriteConcern(WriteConcern.NORMAL);

        return mongoClient;
    }


    @Bean
    public MongoTemplate mongoTemplate() throws Exception{

        return new MongoTemplate(mongo(), getDatabaseName());
    }
}
