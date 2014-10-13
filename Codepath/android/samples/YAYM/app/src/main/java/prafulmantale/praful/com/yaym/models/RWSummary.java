package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by prafulmantale on 10/8/14.
 */
public class RWSummary {

    private static final String TAG = RWSummary.class.getName();

    //"summary":{"nrp":3.2,"yld":123.3,"vol":5.086220669E8,"rpnl":62767.24,"upnl":-49.86,"pnl":62717.38,"tlim":3103007.5,"tacc":98979.13}

    private double netRiskPercentage;
    private double yield;
    private double volume;
    private double realizedPnL;
    private double unrealizedPnL;
    private double pnL;
    private double totalMaxLimit;
    private double totalAccumulation;


    public double getNetRiskPercentage() {
        return netRiskPercentage;
    }

    public void setNetRiskPercentage(double netRiskPercentage) {
        this.netRiskPercentage = netRiskPercentage;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getRealizedPnL() {
        return realizedPnL;
    }

    public void setRealizedPnL(double realizedPnL) {
        this.realizedPnL = realizedPnL;
    }

    public double getUnrealizedPnL() {
        return unrealizedPnL;
    }

    public void setUnrealizedPnL(double unrealizedPnL) {
        this.unrealizedPnL = unrealizedPnL;
    }

    public double getPnL() {
        return pnL;
    }

    public void setPnL(double pnL) {
        this.pnL = pnL;
    }

    public double getTotalMaxLimit() {
        return totalMaxLimit;
    }

    public void setTotalMaxLimit(double totalMaxLimit) {
        this.totalMaxLimit = totalMaxLimit;
    }

    public double getTotalAccumulation() {
        return totalAccumulation;
    }

    public void setTotalAccumulation(double totalAccumulation) {
        this.totalAccumulation = totalAccumulation;
    }

    public static RWSummary fromJSON(JSONObject jsonObject){
        RWSummary summary = new RWSummary();

        try {
            summary.netRiskPercentage = jsonObject.getDouble("nrp");
            summary.yield = jsonObject.getDouble("yld");
            summary.volume = jsonObject.getDouble("vol");
            summary.realizedPnL = jsonObject.getDouble("rpnl");
            summary.unrealizedPnL = jsonObject.getDouble("upnl");
            summary.pnL = jsonObject.getDouble("pnl");
            summary.totalMaxLimit = jsonObject.getDouble("tlim");
            summary.totalAccumulation = jsonObject.getDouble("tacc");
        }
        catch (JSONException ex){
            Log.d(TAG, "Exception while creating object from JSON");
        }

        return summary;
    }

    @Override
    public String toString() {
        return "RWSummary{" +
                "netRiskPercentage=" + netRiskPercentage +
                ", yield=" + yield +
                ", volume=" + volume +
                ", realizedPnL=" + realizedPnL +
                ", unrealizedPnL=" + unrealizedPnL +
                ", pnL=" + pnL +
                ", totalMaxLimit=" + totalMaxLimit +
                ", totalAccumulation=" + totalAccumulation +
                '}';
    }
}