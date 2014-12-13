package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import prafulmantale.praful.com.yaym.helpers.Utils;

/**
 * Created by prafulmantale on 11/24/14.
 */
public class HistoricYieldData {

    private static final String TAG = HistoricYieldData.class.getSimpleName();

    /*
    {"sampleSnapshotList":[{"dPNL":928.39,"cPNL":8376.07,"dVol":1.421389924E7,"cyield":65.4,"ts":1416812400000},{"dPNL":2365.63,"cPNL":10741.7,"dVol":3.744466646E7,"cyield":64.9,"ts":1416816000000},{"dPNL":6500.3,"cPNL":17242.0,"dVol":1.0305796843E8,"cyield":64.19,"ts":1416819600000},{"dPNL":9682.84,"cPNL":26924.84,"dVol":1.5432966767E8,"cyield":63.66,"ts":1416823200000},{"dPNL":2480.57,"cPNL":29405.41,"dVol":3.898962829E7,"cyield":63.66,"ts":1416826800000},{"dPNL":1746.81,"cPNL":31152.22,"dVol":2.824876319E7,"cyield":63.55,"ts":1416830400000},{"dPNL":1203.58,"cPNL":32355.8,"dVol":2.064790154E7,"cyield":63.34,"ts":1416834000000},{"dPNL":2503.42,"cPNL":34859.22,"dVol":4.293466453E7,"cyield":62.95,"ts":1416837600000},{"dPNL":9427.49,"cPNL":44286.72,"dVol":1.4420228011E8,"cyield":63.45,"ts":1416841200000},{"dPNL":3121.16,"cPNL":47407.87,"dVol":4.748942235E7,"cyield":63.59,"ts":1416844800000},{"dPNL":2227.42,"cPNL":49635.29,"dVol":3.529624304E7,"cyield":63.57,"ts":1416848400000},{"dPNL":434.01,"cPNL":50069.3,"dVol":7130111.53,"cyield":63.55,"ts":1416852000000},{"dPNL":642.91,"cPNL":50712.2,"dVol":9183383.32,"cyield":63.62,"ts":1416855600000},{"dPNL":366.61,"cPNL":51078.81,"dVol":5789224.88,"cyield":63.62,"ts":1416859200000},{"dPNL":775.43,"cPNL":51854.24,"dVol":1.116529141E7,"cyield":63.7,"ts":1416862800000},{"dPNL":794.75,"cPNL":52648.99,"dVol":1.246861821E7,"cyield":63.7,"ts":1416866400000},{"dPNL":589.43,"cPNL":589.43,"dVol":1.016244341E7,"cyield":58.0,"ts":1416870000000},{"dPNL":500.92,"cPNL":1090.35,"dVol":1.797489222E7,"cyield":38.75,"ts":1416873600000},{"dPNL":668.42,"cPNL":1758.77,"dVol":1.038529853E7,"cyield":45.65,"ts":1416877200000},{"dPNL":641.4,"cPNL":2400.17,"dVol":1.166233343E7,"cyield":47.82,"ts":1416880800000},{"dPNL":353.0,"cPNL":2753.17,"dVol":5347270.64,"cyield":49.57,"ts":1416884400000},{"dPNL":147.34,"cPNL":2900.5,"dVol":2694293.24,"cyield":49.81,"ts":1416888000000},{"dPNL":357.83,"cPNL":3258.33,"dVol":5497510.05,"cyield":51.13,"ts":1416891600000},{"dPNL":493.96,"cPNL":3752.29,"dVol":7231523.12,"cyield":52.88,"ts":1416895200000}],"status":"OK","errorCode":null,"responseTuples":null}

     */
    private double donePnL;
    private double currentPnL;
    private double doneVolume;
    private double currentYield;
    private long timestamp;
    private  String displayTimestamp;

    public HistoricYieldData() {
    }

    public double getDonePnL() {
        return donePnL;
    }

    public void setDonePnL(double donePnL) {
        this.donePnL = donePnL;
    }

    public double getCurrentPnL() {
        return currentPnL;
    }

    public void setCurrentPnL(double currentPnL) {
        this.currentPnL = currentPnL;
    }

    public double getDoneVolume() {
        return doneVolume;
    }

    public void setDoneVolume(double doneVolume) {
        this.doneVolume = doneVolume;
    }

    public double getCurrentYield() {
        return currentYield;
    }

    public void setCurrentYield(double currentYield) {
        this.currentYield = currentYield;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getDisplayTimestamp() {
        return displayTimestamp;
    }

    public void setDisplayTimestamp(String displayTimestamp) {
        this.displayTimestamp = displayTimestamp;
    }

    public static HistoricYieldData fromJSON(JSONObject jsonObject){

        if(jsonObject == null){
            return null;
        }

        HistoricYieldData data = new HistoricYieldData();

        try {
            data.donePnL = jsonObject.getDouble("dPNL");
            data.currentPnL = jsonObject.getDouble("cPNL");
            data.doneVolume = jsonObject.getDouble("dVol");
            data.currentYield = jsonObject.getDouble("cyield");
            data.timestamp = jsonObject.getLong("ts");

            data.displayTimestamp = Utils.scaleTimeFormat.format(new Date(data.timestamp));
        }
        catch (JSONException jex){
            data = null;

            Log.e(TAG, "Exception while creating data from JSONObject: " + jex.getMessage());
            jex.printStackTrace();
        }

        return data;
    }

    public static List<HistoricYieldData> fromJSON(JSONArray jsonArray){

        List<HistoricYieldData> list = new ArrayList<HistoricYieldData>();

        if(jsonArray == null){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i ++){

            JSONObject jsonObject = null;

            try {
                jsonObject = jsonArray.getJSONObject(i);
            }
            catch (JSONException jex){

                Log.e(TAG, "Exception while extracting JSONObject from array: " + jex.getMessage());
                jex.printStackTrace();
            }

            if(jsonObject == null){
                continue;
            }

            HistoricYieldData data = fromJSON(jsonObject);

            if(data == null){
                continue;
            }

            list.add(data);

        }

        return list;
    }
}
