package com.dhpn.controller;

import com.dhpn.model.User;
import com.dhpn.model.UserProfileUpdateRequest;
import com.dhpn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Controller
@RequestMapping(value = "/users")
@Scope("session")
public class UsersController {

    private static final String TAG = UsersController.class.getSimpleName();


    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    List<User> getAllUsers(@RequestParam(value = "pn", required = false) Integer pageNumber){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

//        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name1 = user.getUsername(); //get logged in username

        System.out.println("NAME: " + name);

        return userService.getAllUsers(pageNumber);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User getUserById(@PathVariable String id){

        return userService.findUserById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> updateUser(@RequestBody UserProfileUpdateRequest request){

        boolean isSuccess = false;

        isSuccess = userService.updateUserProfile(request);

        return new ResponseEntity<String>(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}


