package duggleetech.com.guardianangel.application;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by prafulmantale on 3/11/15.
 */
public class AngelApplication extends Application {

    private ObjectMapper objectMapper;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
