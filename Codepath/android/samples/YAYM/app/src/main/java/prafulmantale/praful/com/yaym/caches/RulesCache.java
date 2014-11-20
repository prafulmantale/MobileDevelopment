package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import prafulmantale.praful.com.yaym.models.RiskRules;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class RulesCache {

    private static final String TAG = RulesCache.class.getSimpleName();

    private static RulesCache INSTANCE = new RulesCache();

    private Map<String, RiskRules> cache;
    private List<String> currencyPairsList;

    public RulesCache() {
        cache = new TreeMap<String, RiskRules>();
        currencyPairsList = new ArrayList<String>();
    }

    public static RulesCache getInstance(){
        return INSTANCE;
    }

    public RiskRules getRule(String currencyPair){
        RiskRules rules = null;
        if(cache.containsKey(currencyPair)){
            rules = cache.get(currencyPair);
        }

        return rules;
    }

    public List<String> getCurrencyPairsList(){

        return currencyPairsList;
    }

    public void updateCache(JSONObject jsonObject){

        if(jsonObject == null){
            return;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("rules");
            cache = RiskRules.fromJSON(jsonArray);

//            currencyPairsList.clear();
//            for(String key : cache.keySet()){
//                currencyPairsList.add(key);
//            }
//
//            Collections.sort(currencyPairsList);

            currencyPairsList = new ArrayList<String>(cache.keySet());
        }
        catch(JSONException ex){
            Log.d(TAG, "Exception while populating the Rules cache");
        }
    }
}
