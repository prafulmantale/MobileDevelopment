package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class RiskSettings {

    private static final String TAG = RiskSettings.class.getSimpleName();

    private static RiskSettings INSTANCE = new RiskSettings();

    private String orgName;
    private String activePolicy;
    private String reportingCurrencyPair;
    private List<String> supportedCcyPairs;
    private List<RiskPolicy> riskPolicies;

    private RiskSettings() {
        orgName = "";
        activePolicy = "";
        reportingCurrencyPair = "";
        supportedCcyPairs = new ArrayList<String>();
        riskPolicies = new ArrayList<RiskPolicy>();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getActivePolicy() {
        return activePolicy;
    }

    public void setActivePolicy(String activePolicy) {
        this.activePolicy = activePolicy;
    }

    public String getReportingCurrencyPair() {
        return reportingCurrencyPair;
    }

    public void setReportingCurrencyPair(String reportingCurrencyPair) {
        this.reportingCurrencyPair = reportingCurrencyPair;
    }

    public List<String> getSupportedCcyPairs() {
        return supportedCcyPairs;
    }

    public void setSupportedCcyPairs(List<String> supportedCcyPairs) {
        this.supportedCcyPairs = supportedCcyPairs;
    }

    public List<RiskPolicy> getRiskPolicies() {
        return riskPolicies;
    }

    public void setRiskPolicies(List<RiskPolicy> riskPolicies) {
        this.riskPolicies = riskPolicies;
    }

    public static RiskSettings getInstance(){
        return INSTANCE;
    }

    public static void fromJSON(JSONObject jsonObject){

        RiskSettings settings = getInstance();

        //Extract and populate Risk Settings

        /*{
            status: "OK",
                    errorCode: null,
                responseTuples: null,
                orgSettings: {
            org: "YMSBAQA",
                    supportedCurrencyPairs: [
            "EUR/GBP",
                    "USD/CAD",
                    "USD/JPY",
                    "EUR/CAD",
                    "EUR/JPY"
            ],
            activePolicy: "defaultPolicy",
                    riskPolicies: [
            {
                policyName: "defaultPolicy",
                        policyDisplayName: "Default Policy",
                    notes: null
            }
            ],
            reportingCurrency: "USD"
        }
        }*/

        try {
            settings.orgName = jsonObject.getString("org");
            settings.activePolicy = jsonObject.getString("activePolicy");
            settings.reportingCurrencyPair = jsonObject.getString("reportingCurrency");
            settings.supportedCcyPairs.clear();
            settings.riskPolicies.clear();

            JSONArray ccyPairs = jsonObject.getJSONArray("supportedCurrencyPairs");
            int len = ccyPairs.length();
            for(int i = 0; i <  len; i ++){
                settings.supportedCcyPairs.add(ccyPairs.getString(i));
            }

            JSONArray policies = jsonObject.getJSONArray("riskPolicies");
            settings.riskPolicies = RiskPolicy.fromJSONArray(policies);

        }
        catch (JSONException ex){
            Log.e(TAG, "Exception while extracting settings");
        }
    }
}
