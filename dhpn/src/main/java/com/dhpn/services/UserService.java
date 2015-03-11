package com.dhpn.services;

import com.dhpn.enums.RequestStatus;
import com.dhpn.model.*;
import com.dhpn.repositories.UsersRepository;
import com.dhpn.utils.DateTimeUtils;
import com.dhpn.utils.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by prafulmantale on 3/1/15.
 */
@Service
public class UserService {

    private static final String TAG = UserService.class.getSimpleName();

    @Autowired
    private UsersRepository usersRepository;


    private static int PAGE_SIZE = 30;


    public RegistrationResponse register(RegistrationRequest request){

        RegistrationResponse response = null;
        if(request == null ||
                request.getEmailId() == null || request.getEmailId().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()
                ){

            response = new RegistrationResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_CODE);
            response.setErrorMessage(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_MESSAGE);

            return response;
        }


        User user = findUserByEmailId(request.getEmailId());

        if(user != null){
            response = new RegistrationResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.USER_ALREADY_EXISTS_CODE);
            response.setErrorMessage(ErrorCodes.USER_ALREADY_EXISTS_MESSAGE);

            return response;
        }

        try {
            user = new User();
            user.setEmailId(request.getEmailId());
            user.setPassword(request.getPassword());

            save(user);

            System.out.format("User with user name %s and password %s created and saved successfully.", request.getEmailId(), request.getPassword());

            response = new RegistrationResponse(RequestStatus.SUCCESS);
            return response;
        }
        catch (Exception ex){
            response = new RegistrationResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.UNABLE_TO_REGISTER_USER_CODE);
            response.setErrorMessage(ErrorCodes.UNABLE_TO_REGISTER_USER_MESSAGE);
            return response;
        }
    }

    public LoginResponse login(LoginRequest request){

        LoginResponse response = null;
        if(request == null ||
                request.getEmailId() == null || request.getEmailId().isEmpty() ||
                request.getPassword() == null || request.getPassword().isEmpty()
                ){

            response = new LoginResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_CODE);
            response.setErrorMessage(ErrorCodes.REQUEST_WITH_INSUFFICIENT_OR_INCORRECT_DATA_MESSAGE);

            return response;
        }


        User user = findUserByEmailId(request.getEmailId());

        if(user == null){
            response = new LoginResponse(RequestStatus.FAILURE);
            response.setErrorCode(ErrorCodes.USER_DOES_NOT_EXIST_CODE);
            response.setErrorMessage(ErrorCodes.USER_DOES_NOT_EXIST_MESSAGE);

            return response;
        }

        if(user.getEmailId().equals(request.getEmailId()) &&
                user.getPassword().equals(request.getPassword())){
            response = new LoginResponse(RequestStatus.SUCCESS);
            return response;
        }

        response = new LoginResponse(RequestStatus.FAILURE);
        response.setErrorCode(ErrorCodes.AUTHENTICATION_FAILED_CODE);
        response.setErrorMessage(ErrorCodes.AUTHENTICATION_FAILED_MESSAGE);
        return response;
    }


    public User findUserByEmailId(String emailId){

        if(emailId == null || emailId.isEmpty()){
            return null;
        }

        List<User> users = usersRepository.findByEmailId(emailId);

        if(users == null || users.size() == 0){
            return null;
        }

        if(users.size() > 1){
            System.out.println("There are more than one users with the given email id. Something is wrong!!!!");
        }

        User user = users.get(0);

        return user;
    }


    public List<User> getAllUsers(Integer pageNumber){

        if(pageNumber == null){
            pageNumber = 0;
        }

        Page<User> page = usersRepository.findAll(new PageRequest(pageNumber, PAGE_SIZE));

        Iterator<User> list =  page.iterator();
        List<User> users = new ArrayList<User>();
        while(list.hasNext()){
            users.add(list.next());
        }

        return users;
    }

    public User findUserById(String id){

        if(id == null || id.isEmpty()){
            return null;
        }

        User user = usersRepository.findOne(id);

        if(user == null){
            user = findUserByEmailId(id);
        }

        return user;
    }


    public boolean updateUserProfile(UserProfileUpdateRequest request){

        if(request == null || request.getEmailId() == null || request.getEmailId().isEmpty()){

            return false;
        }

        User user = findUserByEmailId(request.getEmailId());

        if(user == null){
            System.out.println("Something is wrong..");
            return false;
        }


        if(request.getFirstName() != null && !request.getFirstName().isEmpty()) {
            user.setFirstName(request.getFirstName());
        }

        if(request.getLastName() != null && !request.getLastName().isEmpty()) {
            user.setLastName(request.getLastName());
        }

        if(request.getContacts() != null && request.getContacts().size() > 0) {

            for(ContactInfo contactInfo : request.getContacts()) {
                if (!user.getContacts().contains(contactInfo)) {
                    request.getContacts().add(contactInfo);
                }
            }
        }


        if(request.getAddress() != null) {

            Address  address = request.getAddress();

            Address address1 = user.getAddress();
            if(address1 == null){
                address1 = new Address();
            }

            if(address.getAddressLine1() != null && !address.getAddressLine1().isEmpty()){
                address1.setAddressLine1(address.getAddressLine1());
            }

            if(address.getAddressLine2() != null && !address.getAddressLine2().isEmpty()){
                address1.setAddressLine2(address.getAddressLine2());
            }

            if(address.getCity() != null && !address.getCity().isEmpty()){
                address1.setCity(address.getCity());
            }

            if(address.getCountry() != null && !address.getCountry().isEmpty()){
                address1.setCountry(address.getCountry());
            }

            if(address.getPinCode() != null && !address.getPinCode().isEmpty()){
                address1.setPinCode(address.getPinCode());
            }

            if(address.getState() != null && !address.getState().isEmpty()){
                address1.setState(address.getState());
            }

            user.setAddress(address1);
        }

        save(user);

        return true;
    }


    public void save(User user){
        user.setLastUpdated(System.currentTimeMillis());
        user.setDisplayLastUpdateTime(DateTimeUtils.getFormattedCurrentTime());
        usersRepository.save(user);
    }

}
