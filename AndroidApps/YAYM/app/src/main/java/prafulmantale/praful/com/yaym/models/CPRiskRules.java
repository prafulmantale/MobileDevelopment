package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CPRiskRules {

    private static final String TAG = CPRiskRules.class.getSimpleName();

    private static final CPRiskRules INSTANCE = new CPRiskRules();
    private List<CPRiskRule> riskRules;

    private Map<String, CPRiskRule> lookupCache;

    private CPRiskRules(){
        lookupCache = new HashMap<String, CPRiskRule>();
        riskRules = new ArrayList<CPRiskRule>();
    }

    public static CPRiskRules getInstance(){
        return INSTANCE;
    }

    public CPRiskRule getRiskRule(String ccyPair){

        if(ccyPair == null || ccyPair.isEmpty()){
            return null;
        }

        if(lookupCache.containsKey(ccyPair)){
            return lookupCache.get(ccyPair);
        }

        return null;
    }

    public static void fromJSON(JSONObject jsonObject){

        CPRiskRules rules = getInstance();

        try {
            JSONArray jsonArray = jsonObject.getJSONArray("cpRiskRules");
            rules.riskRules = CPRiskRule.fromJSONArray(jsonArray);
            rules.updateCache();

        }
        catch (JSONException ex){
            Log.e(TAG, "Exception while extracting JSONArray for RiskRules");
            ex.printStackTrace();
        }
    }

    private void updateCache(){

        lookupCache.clear();

        for(CPRiskRule riskRule : riskRules){
            if(riskRule == null || riskRule.getCcyPair() == null || riskRule.getCcyPair().isEmpty()){
                continue;
            }

            lookupCache.put(riskRule.getCcyPair(), riskRule);
        }
    }
}
