package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import prafulmantale.praful.com.yaym.helpers.Utils;

/**
 * Created by prafulmantale on 11/24/14.
 */
public class OHLCData {

    private static final String TAG = OHLCData.class.getSimpleName();

    //"1416812400000,1.23946,1.23978,1.24057,1.23899,3343699968"
    private long timestamp;
    private double open;
    private double close;
    private double high;
    private double low;
    private String displayTimestamp;

    public OHLCData() {
    }

    public static OHLCData fromJSON(String jsonArray){

        if(jsonArray == null){
            return null;
        }

        String []arr = jsonArray.split(",");
        int len = arr.length;

        if(len < 6){
            return null;
        }

        OHLCData data = new OHLCData();

        try {
            data.timestamp = Long.parseLong(arr[0]);
            data.open = Double.parseDouble(arr[1]);
            data.close = Double.parseDouble(arr[2]);
            data.high = Double.parseDouble(arr[3]);
            data.low = Double.parseDouble(arr[4]);

            data.displayTimestamp = Utils.scaleTimeFormat.format(new Date(data.timestamp));
        }
        catch (Exception jex){

            data = null;

            Log.e(TAG, "Exception while extracting data from json array: " + jex.getMessage());
            jex.printStackTrace();
        }

        return data;
    }

    public static List<OHLCData> fromJSONList(JSONArray jsonArray){
        List<OHLCData> list = new ArrayList<OHLCData>();

        if(jsonArray == null){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i++){

            String array = null;

            try {
                array = jsonArray.getString(i);
            }
            catch (JSONException jex){
                Log.e(TAG, "Exception while extracting json array from json array: " + jex.getMessage());
                jex.printStackTrace();
            }

            if(array == null){
                continue;
            }

            OHLCData data = fromJSON(array);

            if(data == null){
                continue;
            }

            list.add(data);
        }

        return list;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public String getDisplayTimestamp() {
        return displayTimestamp;
    }

    public void setDisplayTimestamp(String displayTimestamp) {
        this.displayTimestamp = displayTimestamp;
    }
}
