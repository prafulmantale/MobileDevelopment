package prafulmantale.praful.com.yaym.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RWPollService extends Service {

    private static final String TAG = RWPollService.class.getSimpleName();
    private static final long POLL_FREQUENCY = 3000;

    private Looper looper;
    private Poller poller;
    private boolean isPolling = false;


    @Override
    public void onCreate() {
        super.onCreate();
        poller = new Poller();
        Log.d(TAG, "OnCreated");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        Log.d(TAG, "OnStart");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        synchronized (this) {
            if (isPolling == false) {
                isPolling = true;
                poller.start();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        synchronized (this) {
            Log.d(TAG, "onDestroy");
            poller.interrupt();
            isPolling = false;
            poller = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class Poller extends Thread{

        public Poller(){
            super("Poller");
        }

        @Override
        public void run() {

            while(isPolling) {
                Log.d(TAG, "Poller.run");

                try {
                    sleep(POLL_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    isPolling = false;
                }
            }
        }
    }
}
