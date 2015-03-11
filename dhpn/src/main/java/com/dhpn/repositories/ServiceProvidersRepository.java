package com.dhpn.repositories;

import com.dhpn.model.ServiceProvider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Repository
public interface ServiceProvidersRepository extends MongoRepository<ServiceProvider, String> {

    public List<ServiceProvider> findByFirstNameLike(String name);
    public List<ServiceProvider> findByLastNameLike(String name);
    public List<ServiceProvider> findByFirstNameOrLastNameLike(String name);
}
