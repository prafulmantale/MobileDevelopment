package com.dhpn.repositories;

import com.dhpn.model.LogEvent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by prafulmantale on 3/2/15.
 */

@Repository
public interface LogEventRepository extends MongoRepository<LogEvent, String>{

}
