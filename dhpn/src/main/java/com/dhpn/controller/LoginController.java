package com.dhpn.controller;

import com.dhpn.enums.RequestStatus;
import com.dhpn.model.LoginRequest;
import com.dhpn.model.LoginResponse;
import com.dhpn.model.RegistrationRequest;
import com.dhpn.model.RegistrationResponse;
import com.dhpn.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Controller
@Scope("session")
public class LoginController {

    private static final String TAG = LoginController.class.getSimpleName();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public @ResponseBody
    LoginResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){

        HttpSession httpSession = httpServletRequest.getSession(false);

        if(httpSession == null){
            httpSession = httpServletRequest.getSession(true);
        }


        LoginResponse response = userService.login(request);



        return response;
    }
}
