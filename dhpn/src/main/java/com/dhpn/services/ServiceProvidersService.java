package com.dhpn.services;

import com.dhpn.enums.IdentificationType;
import com.dhpn.model.Identification;
import com.dhpn.model.ServiceProvider;
import com.dhpn.model.User;
import com.dhpn.repositories.ServiceProvidersRepository;
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
public class ServiceProvidersService {

    @Autowired
    private ServiceProvidersRepository repository;

    private static int PAGE_SIZE = 30;


    public List<ServiceProvider> getAllServiceProviders(Integer pageNumber){

        if(pageNumber == null){
            pageNumber = 0;
        }

        Page<ServiceProvider> page = repository.findAll(new PageRequest(pageNumber, PAGE_SIZE));

        Iterator<ServiceProvider> list =  page.iterator();
        List<ServiceProvider> providers = new ArrayList<ServiceProvider>();
        while(list.hasNext()){
            providers.add(list.next());
        }

        return providers;
    }

    public List<ServiceProvider> findByFirstNameLike(String name){

        if(name == null || name.isEmpty()){
            return null;
        }

        return repository.findByFirstNameLike(name);

    }


    public List<ServiceProvider> findByLastNameLike(String name){

        if(name == null || name.isEmpty()){
            return null;
        }

        return repository.findByLastNameLike(name);

    }


    public List<ServiceProvider> findByFirstNameLikeOrLastNameLike(String name){

        if(name == null || name.isEmpty()){
            return null;
        }

        return repository.findByFirstNameOrLastNameLike(name);

    }

    public boolean addProvider(ServiceProvider provider){
        save(provider);

        return true;
    }



    public boolean addIdentification(String providerId, String type, String number, String fileUrl){

        if(providerId == null || providerId.isEmpty() == false){
            return false;
        }

        ServiceProvider provider = repository.findOne(providerId);
        if(provider == null){
            return false;
        }

        List<Identification> identifications = provider.getIdentifications();

        if(identifications == null){
            identifications = new ArrayList<Identification>();
            provider.setIdentifications(identifications);
        }

        Identification identification = new Identification();
        identification.setType(IdentificationType.valueOf(type));
        identification.setNumber(number);
        identification.setImageUrl(fileUrl);

        identifications.add(identification);

        save(provider);

        return true;
    }

    private void save(ServiceProvider provider){
        repository.save(provider);
    }
}
