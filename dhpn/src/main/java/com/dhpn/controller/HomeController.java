package com.dhpn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Controller
public class HomeController {

    private static final String TAG = HomeController.class.getSimpleName();

    @RequestMapping("")
    public String home(){
        return "index";
    }

}
