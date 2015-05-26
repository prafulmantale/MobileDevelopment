package praful.com.kidsonwheels.manager;

import com.squareup.otto.Bus;

/**
 * Created by prafulmantale on 5/26/15.
 */
public final class EventManager {

    private static final Bus INSTANCE = new Bus();

    //Do not change.
    private EventManager(){

    }

    public static Bus getInstance(){
        return INSTANCE;
    }
}
