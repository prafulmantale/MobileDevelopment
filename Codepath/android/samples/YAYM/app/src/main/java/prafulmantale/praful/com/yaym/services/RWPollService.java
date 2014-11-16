package prafulmantale.praful.com.yaym.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.yaym.activities.LoginActivity;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RWPollService extends Service {

    private static final String TAG = RWPollService.class.getSimpleName();
    private static final long POLL_FREQUENCY = 3000;

    private Looper looper;
    private Poller poller;


    @Override
    public void onCreate() {
        super.onCreate();
        poller = new Poller();
        Log.d(TAG, "OnCreated");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        if(poller.isRunning() == false) {
            poller.start();
            poller.setRunning(true);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(poller != null && poller.isRunning()){
            poller.setRunning(false);
            poller.interrupt();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class Poller extends Thread implements NetworkResponseListener {

        private boolean running = false;

        public Poller(){
            super("Poller");
        }

        @Override
        public void run() {

            while(isRunning()) {
                Log.d(TAG, "Poller.run");

                try {
                    LoginActivity.client.getRWSnapshot(new NetworkResponseHandler(this, APIRequest.SNAPSHOT), LoginActivity.cookieStore);
                    sleep(POLL_FREQUENCY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    setRunning(false);
                }
            }
        }

        @Override
        public void OnNetworkResponseReceived(RequestStatus status, APIRequest requestType, Object responseObject) {

            //System.out.println(requestType.toString() + "|" + status + "|" + responseObject);
            if(APIRequest.SNAPSHOT == requestType) {

                if(status == RequestStatus.SUCCESS){

                    try {
                        JSONObject obj = (JSONObject) responseObject;

                        JSONArray arr = obj.getJSONArray("rwpositionSnapshot");
                        List<RWPositionSnapshot> list = RWPositionSnapshot.fromJSON(arr);

                        if (list != null && list.size() > 0) {
                            SnapshotCache.getInstance().update(list);
                        }

                        JSONObject summary = obj.getJSONObject("summary");
                        RWSummary rwSummary = RWSummary.fromJSON(summary);

                    }
                    catch (JSONException ex){
                        Log.d(TAG, "Excption while extracting Snapshots");
                        ex.printStackTrace();
                    }
                }
            }
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
    }
}
