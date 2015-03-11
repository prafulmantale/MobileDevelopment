package com.dhpn.controller;

import com.dhpn.model.ServiceProvider;
import com.dhpn.services.ServiceProvidersService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Controller
@RequestMapping(value = "/providers")
@Scope("session")
public class ServiceProvidersController {

    @Autowired
    private ServiceProvidersService serviceProvidersService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody
    List<ServiceProvider> getAllServiceProviders(@RequestParam(value = "pn", required = false) Integer pageNumber){

        return serviceProvidersService.getAllServiceProviders(pageNumber);
    }

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public @ResponseBody
    List<ServiceProvider> getAllServiceProviders(@RequestParam(value = "like") String name){
        return serviceProvidersService.findByFirstNameLike(name);
    }



    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> addProvider(@RequestBody ServiceProvider provider){
        serviceProvidersService.addProvider(provider);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/idupload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadIds(@RequestParam(value = "provid", required = true) String providerId,
                                            @RequestParam(value = "type", required = true) String type,
                                                @RequestParam(value = "number", required = true) String number,
                                                @RequestParam("file") MultipartFile file){
        boolean isSuccess = false;
        String fileUrl = "";

        if (!file.isEmpty()) {
            try {

                String home = System.getProperty("user.home");

                File dir = new File(home + File.separator + "images");

                if(!dir.exists()){
                    dir.mkdir();
                }
                byte[] bytes = file.getBytes();

                String fileName = String.format("%s.%s",  RandomStringUtils.randomAlphabetic(16), "jpg");

                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);

                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                fileUrl = serverFile.getName();

                isSuccess = true;

            } catch (Exception e) {

            }
        } else {

        }

        if(isSuccess) {
            isSuccess = serviceProvidersService.addIdentification(providerId, type, number, fileUrl);
        }

        return new ResponseEntity<String>(isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
