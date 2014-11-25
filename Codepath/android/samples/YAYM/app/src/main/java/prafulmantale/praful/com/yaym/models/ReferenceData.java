package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 11/24/14.
 */
public class ReferenceData {

    private static final String TAG = ReferenceData.class.getSimpleName();

    /*
    {"instrument":"AEX/USD","instrumentType":"CURRENCY","spotPrecision":2,"spotPointsPrecision":2,"forwardPointsPrecision":2,"pipsFactor":100.0,"inverseSpotPrecision":2,"inverseSpotPointsPrecision":2,"inverseForwardPointsPrecision":2,"inversePipsFactor":100.0,
    "spotValueDate":"2014-11-28","nonDeliverable":false,"sefsupported":false,"clearingExempt":false}
     */
    private String instrument;
    private int spotPrecision;
    private int spotPointsPrecision;
    private int forwardPointsPrecision;
    private double pipsFactor;
    private String spotValueDate;

    public ReferenceData() {
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getSpotPrecision() {
        return spotPrecision;
    }

    public void setSpotPrecision(int spotPrecision) {
        this.spotPrecision = spotPrecision;
    }

    public int getSpotPointsPrecision() {
        return spotPointsPrecision;
    }

    public void setSpotPointsPrecision(int spotPointsPrecision) {
        this.spotPointsPrecision = spotPointsPrecision;
    }

    public int getForwardPointsPrecision() {
        return forwardPointsPrecision;
    }

    public void setForwardPointsPrecision(int forwardPointsPrecision) {
        this.forwardPointsPrecision = forwardPointsPrecision;
    }

    public double getPipsFactor() {
        return pipsFactor;
    }

    public void setPipsFactor(double pipsFactor) {
        this.pipsFactor = pipsFactor;
    }

    public String getSpotValueDate() {
        return spotValueDate;
    }

    public void setSpotValueDate(String spotValueDate) {
        this.spotValueDate = spotValueDate;
    }

    public static ReferenceData fromJSON(JSONObject jsonObject){
        if(jsonObject == null){
            return null;
        }

        ReferenceData data = new ReferenceData();

        try{

            data.instrument = jsonObject.getString("instrument");
            data.spotPrecision = jsonObject.getInt("spotPrecision");
            data.spotPointsPrecision = jsonObject.getInt("spotPointsPrecision");
            data.forwardPointsPrecision = jsonObject.getInt("forwardPointsPrecision");
            data.pipsFactor = jsonObject.getDouble("pipsFactor");
            data.spotValueDate = jsonObject.getString("spotValueDate");


        }
        catch (JSONException jex){
            Log.e(TAG, "Exception while extracting reference data from JSONObject: " + jex.getMessage());
            jex.printStackTrace();

            data = null;
        }

        return data;
    }

    public static List<ReferenceData> fromJSON(JSONArray jsonArray){

        List<ReferenceData> list = new ArrayList<ReferenceData>();

        if(jsonArray == null){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i++){

            JSONObject jsonObject = null;

            try {
                jsonObject = jsonArray.getJSONObject(i);
            }
            catch (JSONException jex){
                Log.e(TAG, "Exception while extracting JSONObject data from JSONArray: " + jex.getMessage());
                jex.printStackTrace();
            }

            if(jsonObject == null){
                continue;
            }

            ReferenceData data = fromJSON(jsonObject);

            if(data == null){
                continue;
            }

            list.add(data);
        }

        return list;
    }
}
