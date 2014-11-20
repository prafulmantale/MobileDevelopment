package prafulmantale.praful.com.yaym.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import prafulmantale.praful.com.yaym.application.YMApplication;
import prafulmantale.praful.com.yaym.caches.SnapshotCache;
import prafulmantale.praful.com.yaym.enums.APIRequest;
import prafulmantale.praful.com.yaym.enums.RequestStatus;
import prafulmantale.praful.com.yaym.handlers.NetworkResponseHandler;
import prafulmantale.praful.com.yaym.helpers.AppConstants;
import prafulmantale.praful.com.yaym.interfaces.NetworkResponseListener;
import prafulmantale.praful.com.yaym.models.RWPositionSnapshot;
import prafulmantale.praful.com.yaym.models.RWSummary;

/**
 * Created by prafulmantale on 11/16/14.
 */
public class RefreshService extends IntentService implements NetworkResponseListener {

    private static final String TAG = RefreshService.class.getSimpleName();
    private YMApplication application;

    public RefreshService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (YMApplication)getApplication();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");

        application.getClient().getRWSnapshot(new NetworkResponseHandler(this, APIRequest.SNAPSHOT));

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
                finally {
                    Intent intent = new Intent(AppConstants.RW_SNAPSHOT_RECEIVED);
                    sendBroadcast(intent);
                }
            }
        }
    }
}
