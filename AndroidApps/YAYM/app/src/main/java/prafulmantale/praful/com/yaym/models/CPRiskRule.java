package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.enums.RiskMode;

/**
 * Created by prafulmantale on 12/12/14.
 */
public class CPRiskRule {

    private static final String TAG = CPRiskRule.class.getSimpleName();

    private long id;
    private int version;

    private String ccyPair;

    private String namespace;
    private String orgName;


    private String policyName;
    private String shortName;

    private double maxLimitLong;
    private double maxLimitShort;

    private double maintenanceLimitLongValue;
    private double maintenanceLimitLong;

    private  double maintenanceLimitShortValue;
    private double maintenanceLimitShort;

    private double unrealizedPnLTakeProfit;
    private double unrealizedPnLStopLoss;

    private boolean timerEnabled;
    private long timerInterval;

    private boolean active;

    private String riskRuleType;
    private RiskMode riskMode;

    public CPRiskRule() {
        id = 0;
        version = 0;
        ccyPair = "";
        namespace = "";
        orgName = "";
        policyName = "";
        shortName = "";
        maxLimitLong = 0;
        maxLimitShort = 0;
        maintenanceLimitLongValue = 0;
        maintenanceLimitLong = 0;
        maintenanceLimitShortValue = 0;
        maintenanceLimitShort = 0;
        unrealizedPnLTakeProfit = 0;
        unrealizedPnLStopLoss = 0;
        timerEnabled = false;
        timerInterval = 0;

        active = false;
        riskRuleType = "";
        riskMode = RiskMode.AUTO;
    }

    public static List<CPRiskRule> fromJSONArray(JSONArray jsonArray){

        List<CPRiskRule> list = new ArrayList<CPRiskRule>();

        if(jsonArray == null || jsonArray.length() == 0){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i ++){

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                CPRiskRule rule = fromJSON(jsonObject);

                if(rule == null){
                    continue;
                }

                list.add(rule);
            }
            catch (JSONException ex){
                Log.e(TAG, "Exception while extracting RiskRule JSONObject from JSONArray");
                ex.printStackTrace();
                continue;
            }
        }

        return list;
    }


    public static CPRiskRule fromJSON(JSONObject jsonObject){

        CPRiskRule riskRule = new CPRiskRule();

        try{

            riskRule.id = jsonObject.optLong("_id");
            riskRule.version = jsonObject.optInt("versionId");
            riskRule.ccyPair = jsonObject.getString("currencyPair");
            riskRule.namespace = jsonObject.getString("namespaceName");
            riskRule.orgName = jsonObject.getString("org");
            riskRule.policyName = jsonObject.getString("policyName");
            riskRule.shortName = jsonObject.getString("shortName");
            riskRule.maxLimitLong = jsonObject.getDouble("maxLimitLong");
            riskRule.maxLimitShort = jsonObject.getDouble("maxLimitShort");
            riskRule.maintenanceLimitLongValue = jsonObject.getDouble("maintenanceLimitLongValue");
            riskRule.maintenanceLimitLong = jsonObject.getDouble("maintenanceLimitLong");
            riskRule.maintenanceLimitShortValue = jsonObject.getDouble("maintenanceLimitShortValue");
            riskRule.maintenanceLimitShort = jsonObject.getDouble("maintenanceLimitShort");
            riskRule.unrealizedPnLTakeProfit = jsonObject.getDouble("uPNLTakeProfit");
            riskRule.unrealizedPnLStopLoss = jsonObject.getDouble("uPNLStopLoss");
            riskRule.timerEnabled = jsonObject.getBoolean("timerEnabled");
            riskRule.timerInterval = jsonObject.getLong("timerInterval");
            riskRule.active = jsonObject.getBoolean("active");
            riskRule.riskRuleType = jsonObject.getString("riskRuleType");
            riskRule.riskMode = RiskMode.valueOf(jsonObject.getString("riskMode"));
        }
        catch (JSONException ex){
            Log.e(TAG, "Exception while extracting RiskRule from JSONObject");
            ex.printStackTrace();
            riskRule = null;
        }

        return riskRule;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public double getMaxLimitLong() {
        return maxLimitLong;
    }

    public void setMaxLimitLong(double maxLimitLong) {
        this.maxLimitLong = maxLimitLong;
    }

    public double getMaxLimitShort() {
        return maxLimitShort;
    }

    public void setMaxLimitShort(double maxLimitShort) {
        this.maxLimitShort = maxLimitShort;
    }

    public double getMaintenanceLimitLongValue() {
        return maintenanceLimitLongValue;
    }

    public void setMaintenanceLimitLongValue(double maintenanceLimitLongValue) {
        this.maintenanceLimitLongValue = maintenanceLimitLongValue;
    }

    public double getMaintenanceLimitLong() {
        return maintenanceLimitLong;
    }

    public void setMaintenanceLimitLong(double maintenanceLimitLong) {
        this.maintenanceLimitLong = maintenanceLimitLong;
    }

    public double getMaintenanceLimitShortValue() {
        return maintenanceLimitShortValue;
    }

    public void setMaintenanceLimitShortValue(double maintenanceLimitShortValue) {
        this.maintenanceLimitShortValue = maintenanceLimitShortValue;
    }

    public double getMaintenanceLimitShort() {
        return maintenanceLimitShort;
    }

    public void setMaintenanceLimitShort(double maintenanceLimitShort) {
        this.maintenanceLimitShort = maintenanceLimitShort;
    }

    public double getUnrealizedPnLTakeProfit() {
        return unrealizedPnLTakeProfit;
    }

    public void setUnrealizedPnLTakeProfit(double unrealizedPnLTakeProfit) {
        this.unrealizedPnLTakeProfit = unrealizedPnLTakeProfit;
    }

    public double getUnrealizedPnLStopLoss() {
        return unrealizedPnLStopLoss;
    }

    public void setUnrealizedPnLStopLoss(double unrealizedPnLStopLoss) {
        this.unrealizedPnLStopLoss = unrealizedPnLStopLoss;
    }

    public boolean isTimerEnabled() {
        return timerEnabled;
    }

    public void setTimerEnabled(boolean timerEnabled) {
        this.timerEnabled = timerEnabled;
    }

    public long getTimerInterval() {
        return timerInterval;
    }

    public void setTimerInterval(long timerInterval) {
        this.timerInterval = timerInterval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRiskRuleType() {
        return riskRuleType;
    }

    public void setRiskRuleType(String riskRuleType) {
        this.riskRuleType = riskRuleType;
    }

    public RiskMode getRiskMode() {
        return riskMode;
    }

    public void setRiskMode(RiskMode riskMode) {
        this.riskMode = riskMode;
    }
}
