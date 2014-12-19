package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import prafulmantale.praful.com.yaym.models.ReferenceData;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class RefDataCache {

    private static final String TAG = RefDataCache.class.getSimpleName();

    private static RefDataCache INSTANCE = new RefDataCache();

    private Map<String, ReferenceData> cache;

    public RefDataCache() {
        cache = new TreeMap<String, ReferenceData>();
    }

    public static RefDataCache getInstance(){
        return INSTANCE;
    }

    public ReferenceData getReferenceData(String currencyPair){
        ReferenceData data = null;
        if(cache.containsKey(currencyPair)){
            data = cache.get(currencyPair);
        }

        return data;
    }


    public void updateCache(JSONArray jsonArray){

        if(jsonArray == null){
            return;
        }
        try {

            cache = ReferenceData.fromJSON(jsonArray);
        }
        catch(Exception ex){
            Log.d(TAG, "Exception while populating the RefData cache");
        }
    }

    //Bad stuff --
    public List<String> getCurrencyPairsList(){
        List<String> list = new ArrayList<String>(cache.keySet());
        Collections.sort(list);
        return list;
    }

    public List<ReferenceData> getAllReferenceData(){

        return new ArrayList<ReferenceData>(cache.values());
    }
}
