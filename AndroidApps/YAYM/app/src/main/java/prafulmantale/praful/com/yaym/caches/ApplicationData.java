package prafulmantale.praful.com.yaym.caches;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import prafulmantale.praful.com.yaym.models.HistoricYieldData;
import prafulmantale.praful.com.yaym.models.OHLCData;

/**
 * Created by prafulmantale on 12/22/14.
 */
public class ApplicationData {

    private static final String TAG = ApplicationData.class.getSimpleName();
    private static final ApplicationData INSTANCE = new ApplicationData();

    private static Map<String, HistoricalData> appCache;

    private ApplicationData() {
        appCache = new HashMap<String, HistoricalData>();
    }

    public static ApplicationData getInstance(){
        return INSTANCE;
    }

    public void addData(String ccyPair, Object data){
        if(ccyPair == null || ccyPair.isEmpty()){
            Log.d(TAG, "addData - ccy pair is null or empty");
            return;
        }

        if(data == null ){
            Log.d(TAG, "addData - data is null");
            return;
        }

        HistoricalData historicalData = appCache.get(ccyPair);

        if(historicalData == null){
            historicalData = new HistoricalData();
            appCache.put(ccyPair, historicalData);
        }
        if(data instanceof OHLCData){
            historicalData.setOhlcData((OHLCData)data);
        }

        if(data instanceof HistoricYieldData){
            historicalData.setYieldData((HistoricYieldData)(data));
        }
    }

    private class HistoricalData{
        private OHLCData ohlcData;
        private HistoricYieldData yieldData;

        public OHLCData getOhlcData() {
            return ohlcData;
        }

        public void setOhlcData(OHLCData ohlcData) {
            this.ohlcData = ohlcData;
        }

        public HistoricYieldData getYieldData() {
            return yieldData;
        }

        public void setYieldData(HistoricYieldData yieldData) {
            this.yieldData = yieldData;
        }
    }
}
