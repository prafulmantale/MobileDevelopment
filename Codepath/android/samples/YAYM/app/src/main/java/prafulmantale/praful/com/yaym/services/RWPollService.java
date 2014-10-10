package prafulmantale.praful.com.yaym.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RWPollService extends Service {

    private Looper looper;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
