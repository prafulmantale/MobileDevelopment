package com.dhpn.services;

import com.dhpn.model.User;
import com.dhpn.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by prafulmantale on 3/8/15.
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        User user = userService.findUserByEmailId(emailId);

        if(user == null){
            throw  new UsernameNotFoundException("User with Email Id " + emailId + " not found.");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), getAuthorities());
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        GrantedAuthority authority = new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_ADMIN";
            }
        };

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(authority);
        return authorities;
    }
}
