package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.models.OHLCData;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class RateDataCache {

    private static final String TAG = RateDataCache.class.getSimpleName();

    private static RateDataCache INSTANCE = new RateDataCache();

    private List<OHLCData> cache;
    private OHLCData[] rateData;

    public RateDataCache() {
        cache = new ArrayList<OHLCData>();
    }

    public static RateDataCache getInstance(){
        return INSTANCE;
    }

    public void updateCache(JSONObject jsonObject){

        if(jsonObject == null){
            return;
        }
        try {

            JSONArray jsonArray = jsonObject.getJSONArray("records");
            cache = OHLCData.fromJSONList(jsonArray);

            rateData = new OHLCData[cache.size()];

            for(int i = 0; i < cache.size(); i++){
                rateData[i] = cache.get(i);
            }

            }
        catch(Exception ex){
            Log.d(TAG, "Exception while populating the Rate data cache");
        }
    }

    public OHLCData[] getRateData() {
        return rateData;
    }
}
