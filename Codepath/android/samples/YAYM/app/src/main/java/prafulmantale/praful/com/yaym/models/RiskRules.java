package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class RiskRules {

    private static final String TAG = RiskRules.class.getSimpleName();

    private String currencyPair;
    private boolean active;
    private double maxLimitShort;
    private double maxLimitLong;
    private double profitThreshold;
    private double lossThreshold;

    public RiskRules() {
        currencyPair = "";
        active = false;
        maxLimitLong = 0;
        maxLimitShort = 0;
        profitThreshold = 0;
        lossThreshold = 0;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getMaxLimitShort() {
        return maxLimitShort;
    }

    public void setMaxLimitShort(double maxLimitShort) {
        this.maxLimitShort = maxLimitShort;
    }

    public double getMaxLimitLong() {
        return maxLimitLong;
    }

    public void setMaxLimitLong(double maxLimitLong) {
        this.maxLimitLong = maxLimitLong;
    }

    public double getProfitThreshold() {
        return profitThreshold;
    }

    public void setProfitThreshold(double profitThreshold) {
        this.profitThreshold = profitThreshold;
    }

    public double getLossThreshold() {
        return lossThreshold;
    }

    public void setLossThreshold(double lossThreshold) {
        this.lossThreshold = lossThreshold;
    }


    public static Map<String, RiskRules> fromJSON(JSONArray jsonArray){
        Map<String, RiskRules> rules = new HashMap<String, RiskRules>();

        if(jsonArray == null){
            return rules;
        }
        int length = jsonArray.length();
        for(int i = 0; i < length; i++){
            try {
                JSONObject obj = jsonArray.getJSONObject(i);
                if(obj == null){
                    continue;
                }

                RiskRules riskRules = fromJSON(obj);

                if(riskRules == null){
                    continue;
                }

                if(!rules.containsKey(riskRules.currencyPair)){
                    rules.put(riskRules.currencyPair, riskRules);
                }
            }
            catch (JSONException ex){
                Log.d(TAG, "Exception while extracting JSON Object from array");
            }
        }

        return rules;
    }

    public static RiskRules fromJSON(JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }

        RiskRules riskRules = new RiskRules();

        try {
            riskRules.currencyPair = jsonObject.getString("currencyPair");
            riskRules.active = jsonObject.getBoolean("active");
            riskRules.maxLimitShort = jsonObject.getDouble("maxLimitShort");
            riskRules.maxLimitLong = jsonObject.getDouble("maxLimitLong");
            riskRules.profitThreshold = jsonObject.getDouble("uPNLTakeProfit");
            riskRules.lossThreshold = jsonObject.getDouble("uPNLStopLoss");
        }
        catch(JSONException ex){
            Log.d(TAG, "Excption while extracting Risk Rule from JSON object\r\n" + jsonObject);
            riskRules = null;
        }

        return riskRules;
    }


    @Override
    public String toString() {
        return "RiskRules{" +
                "currencyPair='" + currencyPair + '\'' +
                ", active=" + active +
                ", maxLimitShort=" + maxLimitShort +
                ", maxLimitLong=" + maxLimitLong +
                ", profitThreshold=" + profitThreshold +
                ", lossThreshold=" + lossThreshold +
                '}';
    }
}
