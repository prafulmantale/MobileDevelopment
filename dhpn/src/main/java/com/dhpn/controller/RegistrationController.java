package com.dhpn.controller;

import com.dhpn.enums.RequestStatus;
import com.dhpn.model.RegistrationRequest;
import com.dhpn.model.RegistrationResponse;
import com.dhpn.services.UserService;
import com.dhpn.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by prafulmantale on 3/1/15.
 */

@Controller
public class RegistrationController {

    private static final String TAG = RegistrationController.class.getSimpleName();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody
    RegistrationResponse register(@Valid @RequestBody RegistrationRequest request, BindingResult result){

        RegistrationResponse response = null;

        if(result.hasErrors()){
            response = new RegistrationResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_CODE);
            response.setErrorMessage(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_MESSAGE);
            return response;
        }

        response = userService.register(request);

        return response;
    }
}
