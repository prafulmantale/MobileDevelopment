package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.models.HistoricYieldData;

/**
 * Created by prafulmantale on 10/31/14.
 */
public class HistoricalDataCache {

    private static final String TAG = HistoricalDataCache.class.getSimpleName();

    private static HistoricalDataCache INSTANCE = new HistoricalDataCache();

    private List<HistoricYieldData> cache;
    private double []volumes;
    private double []yields;
    private String []timeStamps;

    public HistoricalDataCache() {
        cache = new ArrayList<HistoricYieldData>();
    }

    public static HistoricalDataCache getInstance(){
        return INSTANCE;
    }

    public void updateCache(JSONArray jsonArray){

        if(jsonArray == null){
            return;
        }
        try {

            cache = HistoricYieldData.fromJSON(jsonArray);
            volumes = new double[cache.size()];
            yields = new double[cache.size()];
            timeStamps = new String[cache.size()];

            for(int i = 0; i < cache.size(); i ++){
                volumes[i] = cache.get(i).getDoneVolume();
            }

            for(int i = 0; i < cache.size(); i ++){
                yields[i] = cache.get(i).getCurrentYield();
                timeStamps[i] = cache.get(i).getDisplayTimestamp();
            }

        }
        catch(Exception ex){
            Log.d(TAG, "Exception while populating the HistoricalData cache");
        }
    }

    public double[] getVolumes() {
        return volumes;
    }

    public double[] getYields() {
        return yields;
    }

    public String[] getTimeStamps() {
        return timeStamps;
    }
}
