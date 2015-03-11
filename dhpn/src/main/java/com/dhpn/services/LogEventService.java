package com.dhpn.services;

import com.dhpn.model.LogEvent;
import com.dhpn.repositories.LogEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 3/2/15.
 */
@Service
public class LogEventService {

    private static final String TAG = LogEventService.class.getSimpleName();


    @Autowired
    private LogEventRepository repository;


    private List<LogEvent> logEvents = new ArrayList<LogEvent>();


    public void recordLog(LogEvent logEvent){

        logEvents.add(logEvent);

        if(logEvents.size() >= 50){
            flush();
        }

    }


    private void save(LogEvent logEvent){
        repository.save(logEvent);
    }


    private void flush(){
        repository.save(logEvents);
        logEvents.clear();
    }

}
