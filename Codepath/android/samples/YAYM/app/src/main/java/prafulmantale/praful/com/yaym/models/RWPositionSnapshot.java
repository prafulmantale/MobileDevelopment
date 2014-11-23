package prafulmantale.praful.com.yaym.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 10/8/14.
 */
public class RWPositionSnapshot {

    private static final String TAG = RWPositionSnapshot.class.getName();
    private static final DecimalFormat jpyRateFormat = new DecimalFormat("0.000");
    private static final DecimalFormat nonJPYRateFormat = new DecimalFormat("0.00000");
    private static final DecimalFormat accumulationFormat = new DecimalFormat("0.0");

    //{"rwpositionSnapshot":[{"org":"YMSBAQA","ccyPair":"EUR/AUD","accumulation":7000.0,"accumulationInUSD":8913.45,"rPNL":5394.34,"rPNLInUSD":4765.36,
    // "uPNL":-27.81593336894006,"uPNLInUSD":-24.57,"totalPNL":5366.52,"totalPNLUSD":4740.78,"skewEnabled":false,"skewSpread":0.0,
    // "averageBuyRate":1.4401937047669915,"averageSellRate":1.4407808316077748,"baseBuyAmount":1.4978E7,"baseSellAmount":-1.4971E7,
    // "termBuyAmount":-2.156992983E7,"termSellAmount":2.157122131E7,"valueDate":1412985600000,"lastModifiedTime":1412804222042,
    // "bidRate":1.43622,"offerRate":1.43629,"midRate":1.43625,
    // "skewedBidRate":1.43622,"skewedOfferRate":1.43629,"volume":1.5618E7,"volumeInUSD":1.98871803E7,"yield":238.38,
    // "timestamp":1412834728156},
    // {"org":"YMSBAQA","ccyPair":"AUD/USD","accumulation":1000.0,"accumulationInUSD":883.4,"rPNL":1382.33,"rPNLInUSD":1382.33,"uPNL":3.433013434971377,"uPNLInUSD":3.433013434971377,"totalPNL":1385.76,"totalPNLUSD":1385.76,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":0.8837269865650286,"averageSellRate":0.88389996568404,"baseBuyAmount":7.8973E7,"baseSellAmount":-7.8972E7,"termBuyAmount":-6.980334809E7,"termSellAmount":6.979057131E7,"valueDate":1413244800000,"lastModifiedTime":1412804145604,"bidRate":0.88716,"offerRate":0.88716,"midRate":0.88716,"skewedBidRate":0.88716,"skewedOfferRate":0.88716,"volume":8.6226E7,"volumeInUSD":7.61720484E7,"yield":18.19,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"EUR/USD","accumulation":-9000.0,"accumulationInUSD":-11460.15,"rPNL":29470.07,"rPNLInUSD":29470.07,"uPNL":4.115351439004877,"uPNLInUSD":4.115351439004877,"totalPNL":29474.19,"totalPNLUSD":29474.19,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":1.273574196827033,"averageSellRate":1.2737227387289995,"baseBuyAmount":1.59094E8,"baseSellAmount":-1.59103E8,"termBuyAmount":-2.026531089E8,"termSellAmount":2.0261801327E8,"valueDate":1413244800000,"lastModifiedTime":1412804153648,"bidRate":1.27417,"offerRate":1.27418,"midRate":1.27417,"skewedBidRate":1.27417,"skewedOfferRate":1.27418,"volume":1.80491E8,"volumeInUSD":2.2982821485E8,"yield":128.24,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"EUR/JPY","accumulation":0.0,"accumulationInUSD":0.0,"rPNL":-66944.82,"rPNLInUSD":-619.15,"uPNL":0.0,"uPNLInUSD":0.0,"totalPNL":-66944.82,"totalPNLUSD":-619.15,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":137.68374265623228,"averageSellRate":137.6763546557786,"baseBuyAmount":8817000.0,"baseSellAmount":-8817000.0,"termBuyAmount":-1.213892419E9,"termSellAmount":1.213957559E9,"valueDate":1413244800000,"lastModifiedTime":1412804881140,"bidRate":137.436,"offerRate":137.441,"midRate":137.4385,"skewedBidRate":137.436,"skewedOfferRate":137.441,"volume":9868000.0,"volumeInUSD":1.25654178E7,"yield":-49.28,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"NZD/USD","accumulation":0.0,"accumulationInUSD":0.0,"rPNL":21369.52,"rPNLInUSD":21369.52,"uPNL":0.0,"uPNLInUSD":0.0,"totalPNL":21369.52,"totalPNLUSD":21369.52,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":0.7903369693805243,"averageSellRate":0.7906531978890836,"baseBuyAmount":5.3626E7,"baseSellAmount":-5.3626E7,"termBuyAmount":-4.239956839E7,"termSellAmount":4.238261032E7,"valueDate":1412985600000,"lastModifiedTime":1412804182835,"bidRate":0.79404,"offerRate":0.7941,"midRate":0.79407,"skewedBidRate":0.79404,"skewedOfferRate":0.7941,"volume":5.4667E7,"volumeInUSD":4.32306636E7,"yield":494.31,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"EUR/GBP","accumulation":-6000.0,"accumulationInUSD":-7640.1,"rPNL":771.92,"rPNLInUSD":1247.74,"uPNL":-2.1263254500529793,"uPNLInUSD":-3.44,"totalPNL":769.79,"totalPNLUSD":1244.29,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":0.7878169593639577,"averageSellRate":0.7879743875750088,"baseBuyAmount":5660000.0,"baseSellAmount":-5666000.0,"termBuyAmount":-4464662.88,"termSellAmount":4459043.99,"valueDate":1413158400000,"lastModifiedTime":1412804835933,"bidRate":0.7876,"offerRate":0.78762,"midRate":0.78761,"skewedBidRate":0.7876,"skewedOfferRate":0.78762,"volume":5889000.0,"volumeInUSD":7498758.15,"yield":165.93,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"USD/CAD","accumulation":62000.0,"accumulationInUSD":62000.0,"rPNL":-4146.96,"rPNLInUSD":-3733.88,"uPNL":-16.979816579750118,"uPNLInUSD":-15.29,"totalPNL":-4163.94,"totalPNLUSD":-3749.17,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":1.1104338680093508,"averageSellRate":1.1101162486437615,"baseBuyAmount":1.1122E7,"baseSellAmount":-1.106E7,"termBuyAmount":-1.227788571E7,"termSellAmount":1.235024548E7,"valueDate":1412899200000,"lastModifiedTime":1412804519147,"bidRate":1.11016,"offerRate":1.11023,"midRate":1.11019,"skewedBidRate":1.11016,"skewedOfferRate":1.11023,"volume":1.1808E7,"volumeInUSD":1.1808E7,"yield":-317.52,"timestamp":1412834728157},{"org":"YMSBAQA","ccyPair":"GBP/USD","accumulation":0.0,"accumulationInUSD":0.0,"rPNL":2020.65,"rPNLInUSD":2020.65,"uPNL":0.0,"uPNLInUSD":0.0,"totalPNL":2020.65,"totalPNLUSD":2020.65,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":1.6165091534955525,"averageSellRate":1.6165425725942806,"baseBuyAmount":2.7206E7,"baseSellAmount":-2.7206E7,"termBuyAmount":-4.397965723E7,"termSellAmount":4.397874803E7,"valueDate":1413244800000,"lastModifiedTime":1412804401493,"bidRate":1.61776,"offerRate":1.6178,"midRate":1.61778,"skewedBidRate":1.61776,"skewedOfferRate":1.6178,"volume":3.109E7,"volumeInUSD":5.025403145E7,"yield":40.2,"timestamp":1412834728156},{"org":"YMSBAQA","ccyPair":"GBP/JPY","accumulation":-5000.0,"accumulationInUSD":-8082.03,"rPNL":224957.91,"rPNLInUSD":2080.56,"uPNL":-1525.1888653467915,"uPNLInUSD":-14.11,"totalPNL":223432.72,"totalPNLUSD":2066.46,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":174.78536237392194,"averageSellRate":174.80803777306934,"baseBuyAmount":1.3682E7,"baseSellAmount":-1.3687E7,"termBuyAmount":-2.392597613E9,"termSellAmount":2.391413328E9,"valueDate":1413244800000,"lastModifiedTime":1412804385782,"bidRate":174.493,"offerRate":174.503,"midRate":174.498,"skewedBidRate":174.493,"skewedOfferRate":174.503,"volume":1.687E7,"volumeInUSD":2.726875235E7,"yield":75.78,"timestamp":1412834728157},{"org":"YMSBAQA","ccyPair":"USD/JPY","accumulation":0.0,"accumulationInUSD":0.0,"rPNL":517267.18,"rPNLInUSD":4784.04,"uPNL":0.0,"uPNLInUSD":0.0,"totalPNL":517267.18,"totalPNLUSD":4784.04,"skewEnabled":false,"skewSpread":0.0,"averageBuyRate":108.08352121900435,"averageSellRate":108.10520450501609,"baseBuyAmount":2.6415E7,"baseSellAmount":-2.6415E7,"termBuyAmount":-2.855598977E9,"termSellAmount":2.855026213E9,"valueDate":1413244800000,"lastModifiedTime":1412804160497,"bidRate":107.861,"offerRate":107.863,"midRate":107.862,"skewedBidRate":107.861,"skewedOfferRate":107.863,"volume":3.0109E7,"volumeInUSD":3.0109E7,"yield":158.89,"timestamp":1412834728156}
    // ],"summary":{"nrp":3.2,"yld":123.3,"vol":5.086220669E8,"rpnl":62767.24,"upnl":-49.86,"pnl":62717.38,"tlim":3103007.5,"tacc":98979.13},"status":"OK","errorCode":null,"responseTuples":null}
    private String org;
    private String currencyPair;

    private double accumulation;
    private double accumulationInUSD;
    private String accumulationDisplay;
    private String accumulationInUSDDisplay;

    private double realizedPnL;
    private double realizedPnLInUSD;
    private String realizedPnLInUSDDisplay;

    private double unrealizedPnL;
    private double unrealizedPnlInUSD;
    private String unrealizedPnLDisplay;
    private String unrealizedPnLInUSDDisplay;

    private double totalPnL;
    private double totalPnLInUSD;

    private boolean skewEnabled;
    private double skewSpread;

    private double avgBuyRate;
    private double avgSellRate;

    private double baseBuyAmnt;
    private double baseSellAmnt;

    private double termBuyAmnt;
    private double termSellAmnt;

    private long valueDate;
    private long lastModifiedTime;
    private long timestamp;

    private double bidRate;
    private double offerRate;
    private double midRate;

    private double skewedBidRate;
    private double skewedOfferRate;

    private double volume;
    private double volumeInUSD;
    private String volumeInUSDDisplay;

    private double yield;
    private String yieldDisplay;

    private boolean isItemSelected;

    private String bigFigure;
    private String pips;
    private String halfPips;


    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public double getAccumulation() {
        return accumulation;
    }

    public String getAccumulationDisplay() {
        return accumulationDisplay;
    }

    public String getAccumulationInUSDDisplay() {
        return accumulationInUSDDisplay;
    }

    public void setAccumulation(double accumulation) {
        this.accumulation = accumulation;
    }

    public double getAccumulationInUSD() {
        return accumulationInUSD;
    }

    public void setAccumulationInUSD(double accumulationInUSD) {
        this.accumulationInUSD = accumulationInUSD;
    }

    public double getRealizedPnL() {
        return realizedPnL;
    }

    public void setRealizedPnL(double realizedPnL) {
        this.realizedPnL = realizedPnL;
    }

    public double getRealizedPnLInUSD() {
        return realizedPnLInUSD;
    }

    public void setRealizedPnLInUSD(double realizedPnLInUSD) {
        this.realizedPnLInUSD = realizedPnLInUSD;
    }

    public String getRealizedPnLInUSDDisplay() {
        return realizedPnLInUSDDisplay;
    }

    public double getUnrealizedPnL() {
        return unrealizedPnL;
    }

    public void setUnrealizedPnL(double unrealizedPnL) {
        this.unrealizedPnL = unrealizedPnL;
    }

    public double getUnrealizedPnlInUSD() {
        return unrealizedPnlInUSD;
    }

    public void setUnrealizedPnlInUSD(double unrealizedPnlInUSD) {
        this.unrealizedPnlInUSD = unrealizedPnlInUSD;
    }

    public double getTotalPnL() {
        return totalPnL;
    }

    public void setTotalPnL(double totalPnL) {
        this.totalPnL = totalPnL;
    }

    public double getTotalPnLInUSD() {
        return totalPnLInUSD;
    }

    public void setTotalPnLInUSD(double totalPnLInUSD) {
        this.totalPnLInUSD = totalPnLInUSD;
    }

    public boolean isSkewEnabled() {
        return skewEnabled;
    }

    public void setSkewEnabled(boolean skewEnabled) {
        this.skewEnabled = skewEnabled;
    }

    public double getSkewSpread() {
        return skewSpread;
    }

    public void setSkewSpread(double skewSpread) {
        this.skewSpread = skewSpread;
    }

    public double getAvgBuyRate() {
        return avgBuyRate;
    }

    public void setAvgBuyRate(double avgBuyRate) {
        this.avgBuyRate = avgBuyRate;
    }

    public double getAvgSellRate() {
        return avgSellRate;
    }

    public void setAvgSellRate(double avgSellRate) {
        this.avgSellRate = avgSellRate;
    }

    public double getBaseBuyAmnt() {
        return baseBuyAmnt;
    }

    public void setBaseBuyAmnt(double baseBuyAmnt) {
        this.baseBuyAmnt = baseBuyAmnt;
    }

    public double getBaseSellAmnt() {
        return baseSellAmnt;
    }

    public void setBaseSellAmnt(double baseSellAmnt) {
        this.baseSellAmnt = baseSellAmnt;
    }

    public double getTermBuyAmnt() {
        return termBuyAmnt;
    }

    public void setTermBuyAmnt(double termBuyAmnt) {
        this.termBuyAmnt = termBuyAmnt;
    }

    public double getTermSellAmnt() {
        return termSellAmnt;
    }

    public void setTermSellAmnt(double termSellAmnt) {
        this.termSellAmnt = termSellAmnt;
    }

    public long getValueDate() {
        return valueDate;
    }

    public void setValueDate(long valueDate) {
        this.valueDate = valueDate;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getBidRate() {
        return bidRate;
    }

    public void setBidRate(double bidRate) {
        this.bidRate = bidRate;
    }

    public double getOfferRate() {
        return offerRate;
    }

    public void setOfferRate(double offerRate) {
        this.offerRate = offerRate;
    }

    public double getMidRate() {
        return midRate;
    }

    public void setMidRate(double midRate) {
        this.midRate = midRate;
    }

    public double getSkewedBidRate() {
        return skewedBidRate;
    }

    public void setSkewedBidRate(double skewedBidRate) {
        this.skewedBidRate = skewedBidRate;
    }

    public double getSkewedOfferRate() {
        return skewedOfferRate;
    }

    public void setSkewedOfferRate(double skewedOfferRate) {
        this.skewedOfferRate = skewedOfferRate;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getVolumeInUSD() {
        return volumeInUSD;
    }

    public void setVolumeInUSD(double volumeInUSD) {
        this.volumeInUSD = volumeInUSD;
    }

    public String getVolumeInUSDDisplay() {
        return volumeInUSDDisplay;
    }

    public void setVolumeInUSDDisplay(String volumeInUSDDisplay) {
        this.volumeInUSDDisplay = volumeInUSDDisplay;
    }

    public double getYield() {
        return yield;
    }

    public void setYield(double yield) {
        this.yield = yield;
    }

    public String getYieldDisplay() {
        return yieldDisplay;
    }

    public void setYieldDisplay(String yieldDisplay) {
        this.yieldDisplay = yieldDisplay;
    }

    public boolean isItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean isItemSelected) {
        this.isItemSelected = isItemSelected;
    }

    public String getBigFigure() {
        return bigFigure;
    }

    public String getPips() {
        return pips;
    }

    public String getHalfPips() {
        return halfPips;
    }

    public String getUnrealizedPnLDisplay() {
        return unrealizedPnLDisplay;
    }

    public String getUnrealizedPnLInUSDDisplay() {
        return unrealizedPnLInUSDDisplay;
    }

    public static RWPositionSnapshot fromJSON(JSONObject jsonObject){
        RWPositionSnapshot rwPositionSnapshot = new RWPositionSnapshot();

        //"org":"YMSBAQA","ccyPair":"EUR/AUD","accumulation":7000.0,"accumulationInUSD":8913.45,"rPNL":5394.34,"rPNLInUSD":4765.36,

        try {
            rwPositionSnapshot.org = jsonObject.getString("org");
            rwPositionSnapshot.currencyPair = jsonObject.getString("ccyPair");

            rwPositionSnapshot.accumulation = jsonObject.getDouble("accumulation");
            rwPositionSnapshot.accumulationInUSD = jsonObject.getDouble("accumulationInUSD");
            rwPositionSnapshot.accumulationDisplay = accumulationFormat.format(rwPositionSnapshot.accumulation / 1000);

            if(rwPositionSnapshot.accumulationInUSD < 0){
                rwPositionSnapshot.accumulationInUSDDisplay = accumulationFormat.format(-rwPositionSnapshot.accumulationInUSD / 1000);
            }
            else {
                rwPositionSnapshot.accumulationInUSDDisplay = accumulationFormat.format(rwPositionSnapshot.accumulationInUSD / 1000);
            }

            rwPositionSnapshot.realizedPnL = jsonObject.getDouble("rPNL");
            rwPositionSnapshot.realizedPnLInUSD = jsonObject.getDouble("rPNLInUSD");
            if(rwPositionSnapshot.realizedPnLInUSD < 0){
                rwPositionSnapshot.realizedPnLInUSDDisplay = accumulationFormat.format(-rwPositionSnapshot.realizedPnLInUSD);
            }
            else {
                rwPositionSnapshot.realizedPnLInUSDDisplay = accumulationFormat.format(rwPositionSnapshot.realizedPnLInUSD);
            }

            // "uPNL":-27.81593336894006,"uPNLInUSD":-24.57,"totalPNL":5366.52,"totalPNLUSD":4740.78,"skewEnabled":false,"skewSpread":0.0,
            rwPositionSnapshot.unrealizedPnL = jsonObject.getDouble("uPNL");
            rwPositionSnapshot.unrealizedPnlInUSD = jsonObject.getDouble("uPNLInUSD");
            rwPositionSnapshot.unrealizedPnLDisplay = accumulationFormat.format(rwPositionSnapshot.unrealizedPnL);
            if(rwPositionSnapshot.unrealizedPnlInUSD < 0){
                rwPositionSnapshot.unrealizedPnLInUSDDisplay = accumulationFormat.format(-rwPositionSnapshot.unrealizedPnlInUSD);
            }
            else {
                rwPositionSnapshot.unrealizedPnLInUSDDisplay = accumulationFormat.format(rwPositionSnapshot.unrealizedPnlInUSD);
            }
            rwPositionSnapshot.totalPnL = jsonObject.getDouble("totalPNL");
            rwPositionSnapshot.totalPnLInUSD = jsonObject.getDouble("totalPNLUSD");
            rwPositionSnapshot.skewEnabled = jsonObject.getBoolean("skewEnabled");
            rwPositionSnapshot.skewSpread = jsonObject.getDouble("skewSpread");

            //averageBuyRate":1.4401937047669915,"averageSellRate":1.4407808316077748,"baseBuyAmount":1.4978E7,"baseSellAmount":-1.4971E7,

            rwPositionSnapshot.avgBuyRate = jsonObject.getDouble("averageBuyRate");
            rwPositionSnapshot.avgSellRate = jsonObject.getDouble("averageSellRate");
            rwPositionSnapshot.baseBuyAmnt = jsonObject.getDouble("baseBuyAmount");
            rwPositionSnapshot.baseSellAmnt = jsonObject.getDouble("baseSellAmount");

            // "termBuyAmount":-2.156992983E7,"termSellAmount":2.157122131E7,"valueDate":1412985600000,"lastModifiedTime":1412804222042,
            // "timestamp":1412834728156},

            rwPositionSnapshot.termBuyAmnt = jsonObject.getDouble("termBuyAmount");
            rwPositionSnapshot.termSellAmnt = jsonObject.getDouble("termBuyAmount");

            rwPositionSnapshot.valueDate = jsonObject.getLong("valueDate");
            rwPositionSnapshot.lastModifiedTime = jsonObject.getLong("lastModifiedTime");
            rwPositionSnapshot.timestamp = jsonObject.getLong("timestamp");

            // "bidRate":1.43622,"offerRate":1.43629,"midRate":1.43625,

            rwPositionSnapshot.bidRate = jsonObject.getDouble("bidRate");
            rwPositionSnapshot.offerRate = jsonObject.getDouble("offerRate");
            rwPositionSnapshot.midRate = jsonObject.getDouble("midRate");
            rwPositionSnapshot.updateFormattedRates();

            // "skewedBidRate":1.43622,"skewedOfferRate":1.43629,"volume":1.5618E7,"volumeInUSD":1.98871803E7,"yield":238.38,

            rwPositionSnapshot.skewedBidRate = jsonObject.getDouble("skewedBidRate");
            rwPositionSnapshot.skewedOfferRate = jsonObject.getDouble("skewedOfferRate");
            rwPositionSnapshot.volume = jsonObject.getDouble("volume");
            rwPositionSnapshot.volumeInUSD = jsonObject.getDouble("volumeInUSD");
            rwPositionSnapshot.yield = jsonObject.getDouble("yield");

        }
        catch (JSONException ex){
            Log.d(TAG, "Exception while creating RWPositionSnapshot from JSON Object");
        }
        return rwPositionSnapshot;
    }

    public static List<RWPositionSnapshot> fromJSON(JSONArray jsonArray){
        List<RWPositionSnapshot> list = new ArrayList<RWPositionSnapshot>();

        if(jsonArray == null){
            return list;
        }

        for(int i = 0; i < jsonArray.length(); i++){

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject == null) {
                    continue;
                }

                list.add(fromJSON(jsonObject));
            }
            catch (JSONException ex){
                Log.d(TAG, "Exception while processing JSON object at index " + i);
            }
        }

        return list;
    }

    private void  updateFormattedRates ()
    {
        String midRateStr = null;
        if(currencyPair.indexOf("JPY") !=-1)
        {
             midRateStr = jpyRateFormat.format(midRate);
        }
        else {
             midRateStr = nonJPYRateFormat.format(midRate);
        }

        bigFigure = midRateStr.substring(0, midRateStr.length() - 3);
        pips = midRateStr.substring(bigFigure.length(), bigFigure.length() + 2);
        halfPips = midRateStr.substring(bigFigure.length() + 2  , bigFigure.length() + 3);
    }


    @Override
    public String toString() {
        return "RWPositionSnapshot{" +
                "org='" + org + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", accumulation=" + accumulation +
                ", accumulationInUSD=" + accumulationInUSD +
                ", realizedPnL=" + realizedPnL +
                ", realizedPnLInUSD=" + realizedPnLInUSD +
                ", unrealizedPnL=" + unrealizedPnL +
                ", unrealizedPnlInUSD=" + unrealizedPnlInUSD +
                ", totalPnL=" + totalPnL +
                ", totalPnLInUSD=" + totalPnLInUSD +
                ", skewEnabled=" + skewEnabled +
                ", skewSpread=" + skewSpread +
                ", avgBuyRate=" + avgBuyRate +
                ", avgSellRate=" + avgSellRate +
                ", baseBuyAmnt=" + baseBuyAmnt +
                ", baseSellAmnt=" + baseSellAmnt +
                ", termBuyAmnt=" + termBuyAmnt +
                ", termSellAmnt=" + termSellAmnt +
                ", valueDate=" + valueDate +
                ", lastModifiedTime=" + lastModifiedTime +
                ", timestamp=" + timestamp +
                ", bidRate=" + bidRate +
                ", offerRate=" + offerRate +
                ", midRate=" + midRate +
                ", skewedBidRate=" + skewedBidRate +
                ", skewedOfferRate=" + skewedOfferRate +
                ", volume=" + volume +
                ", volumeInUSD=" + volumeInUSD +
                ", yield=" + yield +
                '}';
    }
}
