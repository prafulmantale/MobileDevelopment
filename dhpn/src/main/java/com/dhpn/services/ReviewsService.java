package com.dhpn.services;

import com.dhpn.model.Review;
import com.dhpn.model.ServiceProvider;
import com.dhpn.model.User;
import com.dhpn.repositories.ReviewsRepository;
import com.dhpn.repositories.ServiceProvidersRepository;
import com.dhpn.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Service
public class ReviewsService {

    private static final String TAG = ReviewsService.class.getSimpleName();

    @Autowired
    private ReviewsRepository repository;

    @Autowired
    private ServiceProvidersRepository providersRepository;


    @Autowired
    private UsersRepository usersRepository;


    private static int PAGE_SIZE = 10;


    public List<Review> getAllReviewsForProvider(String providerId, Integer pageNumber){

        if(pageNumber == null){
            pageNumber = 0;
        }

        ServiceProvider provider = providersRepository.findOne(providerId);

        if(provider == null){
            System.out.format("Service Provider with id %s not found \r\n", providerId);
            return null;
        }

        List<String> ids = provider.getReviews();

        if(ids == null || ids.size() == 0){
            return null;
        }

        Iterable<Review> all =  repository.findAll(ids);
        Iterator<Review> list =  all.iterator();
        List<Review> reviews = new ArrayList<Review>();
        while(list.hasNext()){
            reviews.add(list.next());
        }

        return reviews;
    }

    public List<Review> getAllReviewsByUser(String userId, Integer pageNumber){

        if(pageNumber == null){
            pageNumber = 0;
        }

        User user = usersRepository.findOne(userId);

        if(user == null){
            System.out.format("User with id %s not found \r\n", userId);
            return null;
        }

        List<String> ids = user.getReviews();

        if(ids == null || ids.size() == 0){
            return null;
        }

        Iterable<Review> all =  repository.findAll(ids);
        Iterator<Review> list =  all.iterator();
        List<Review> reviews = new ArrayList<Review>();
        while(list.hasNext()){
            reviews.add(list.next());
        }

        return reviews;
    }

    public boolean addReview(String userId, String providerId, Review review){

        User user = usersRepository.findOne(userId);

        if(user == null){
            System.out.format("User with id %s not found \r\n", userId);
            return false;
        }

        ServiceProvider provider = providersRepository.findOne(providerId);

        if(provider == null){
            System.out.format("Service Provider with id %s not found \r\n", providerId);
            return false;
        }

        Review savedReview = save(review);

        user.getReviews().add(savedReview.getId());
        provider.getReviews().add(savedReview.getId());

        return true;
    }


    private Review save(Review review){

        Review savedReview = repository.save(review);
        return  savedReview;
    }

}
