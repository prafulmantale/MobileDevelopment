package com.dhpn.repositories;

import com.dhpn.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Repository
public interface UsersRepository  extends MongoRepository<User, String>{

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll();

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll(Sort sort);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(org.springframework.data.domain.Pageable pageable);


    @Override
    @PostAuthorize("returnObject.emailId == principal.username or hasRole('ROLE_ADMIN')")
    User findOne(String aLong);


    public List<User> findByEmailId(String emailId);
}
