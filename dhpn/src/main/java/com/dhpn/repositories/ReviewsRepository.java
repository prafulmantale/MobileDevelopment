package com.dhpn.repositories;

import com.dhpn.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Repository
public interface ReviewsRepository extends MongoRepository<Review, String> {

}
