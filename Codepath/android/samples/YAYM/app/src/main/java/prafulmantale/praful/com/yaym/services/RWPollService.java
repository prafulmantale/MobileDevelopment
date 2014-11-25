package prafulmantale.praful.com.yaym.services;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import prafulmantale.praful.com.yaym.R;
import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.asynctasks.HttpGetAsyncTask;
import prafulmantale.praful.com.yaym.caches.RWSummaryCache;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.helpers.NetworkUtils;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class RWPollService extends Service {

    private static final String TAG = RWPollService.class.getSimpleName();
    private static final long POLL_FREQUENCY = 5000;

    private Looper messageLooper;
    private ResponseHandler handler;


    @Override
    public void onCreate() {

        Log.d(TAG, "onCreate");
        HandlerThread thread = new HandlerThread("RWPollService", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        messageLooper = thread.getLooper();
        handler = new ResponseHandler(messageLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand");
        fetchData();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        Log.d(TAG, "onDestroy");
        if(handler != null){
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class ResponseHandler extends Handler{
        private ResponseHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {

            String response = (String)msg.obj;

            if(response != null && !response.isEmpty()){

                if(response.contains(getString(R.string.error_not_logged_in)) ||
                        response.contains(getString(R.string.error_equal_401))){
                    stopSelf();
                    return;
                }

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    processResponse(jsonObject);
                }
                catch (JSONException jex){
                    Log.e(TAG, "Excetpion in ResponseHandler.handleMessage: " + jex.getMessage());
                    jex.printStackTrace();
                }
                finally {
                    scheduleNextRefresh();
                }
            }
        }
    }

    private void fetchData(){

        new HttpGetAsyncTask(handler, YMApplication.getRWSnapshotUrl(), AppConstants.HandlerMessageIds.SNAPSHOT).execute();
    }

    private void scheduleNextRefresh() {

        long pollInterval = 2000;

        if(NetworkUtils.isConnectedToProvider(getApplicationContext())){
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            int interval = preferences.getInt(AppConstants.PREF_KEY_FREQUENCY, 5);
            if(pollInterval == -1){
                return;
            }

            pollInterval = interval * 1000;
        }



//       handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                fetchData();
//            }
//        }, pollInterval);

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fetchData();
            }
        }, pollInterval);
    }

    private void processResponse(JSONObject jsonObject) {

        try {
            JSONArray arr = jsonObject.getJSONArray("rwpositionSnapshot");
            List<RWPositionSnapshot> list = RWPositionSnapshot.fromJSON(arr);

            if (list != null && list.size() > 0) {
                SnapshotCache.getInstance().update(list);
            }

            JSONObject summary = jsonObject.getJSONObject("summary");
            RWSummary rwSummary = RWSummary.fromJSON(summary);
            RWSummaryCache.getInstance().setRWSummary(rwSummary);
        }
        catch (JSONException ex){
            Log.e(TAG, "Excption while extracting Snapshots: " + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            Intent intent = new Intent(AppConstants.RW_SNAPSHOT_RECEIVED);
            sendBroadcast(intent);
        }
    }
}
