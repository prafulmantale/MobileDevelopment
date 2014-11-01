package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import prafulmantale.praful.com.yaym.models.RiskRules;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class RulesCache {

    private static final String TAG = RulesCache.class.getSimpleName();

    private static RulesCache INSTANCE = new RulesCache();

    private Map<String, RiskRules> cache;

    public RulesCache() {
        cache = new HashMap<String, RiskRules>();
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

    public void updateCache(JSONObject jsonObject){

        if(jsonObject == null){
            return;
        }
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("rules");
            cache = RiskRules.fromJSON(jsonArray);

        }
        catch(JSONException ex){
            Log.d(TAG, "Exception while populating the Rules cache");
        }
    }
}
