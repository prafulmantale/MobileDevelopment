package prafulmantale.praful.com.yaym.models;

import java.text.DecimalFormat;

import prafulmantale.praful.com.yaym.caches.RefDataCache;

/**
 * Created by prafulmantale on 11/28/14.
 */
public class RateProperties {

    private static final String TAG = RateProperties.class.getSimpleName();

    private double rate;
    private double bigFigure;
    private double pips;
    private double fractionalPips;
    private double onePipValue;

    private String rateDisplay;
    private String bigFigureDisplay;
    private String pipsDisplay;
    private String fractionalPipsDisplay;

    private RateProperties(){
        rateDisplay = "";
        bigFigureDisplay = "";
        pipsDisplay = "";
        fractionalPipsDisplay = "";
    }

    public static RateProperties newInstance(String ccyPair, double rate){
        RateProperties rateProperties = new RateProperties();

        ReferenceData refData = RefDataCache.getInstance().getReferenceData(ccyPair);

        if(refData == null || rate == 0){
            return rateProperties;
        }

        rateProperties.rate = rate;
        rateProperties.setRateProperties(refData);

        return  rateProperties;
    }

    private void setRateProperties(ReferenceData referenceData){

        int spotPrecision = referenceData.getSpotPrecision();
        int spotPointsPrecision = referenceData.getSpotPointsPrecision();

        DecimalFormat format = new DecimalFormat();
        format.setMaximumFractionDigits(spotPrecision);
        format.setMinimumFractionDigits(spotPrecision);

        String strRate = format.format(rate);
        int len = strRate.length();

        bigFigureDisplay = strRate.substring(0, len - spotPointsPrecision);
        String allpips = strRate.substring(bigFigureDisplay.length(), len);

        if(allpips.length() > 2){
            pipsDisplay = allpips.substring(0, 2);
            fractionalPipsDisplay = allpips.substring(pipsDisplay.length(), allpips.length());
        }

        if(bigFigureDisplay != null && bigFigureDisplay.length() > 0) {
            bigFigure = Double.parseDouble(bigFigureDisplay);
        }

        if(pipsDisplay != null && pipsDisplay.length() > 0) {
            pips = Integer.parseInt(pipsDisplay);
        }

        if(fractionalPipsDisplay != null && fractionalPipsDisplay.length() > 0) {
            fractionalPips = Integer.parseInt(fractionalPipsDisplay);
        }

        onePipValue = 1/Math.pow(10, spotPrecision - spotPointsPrecision + 2);
    }


    public double getRate() {
        return rate;
    }

    public double getBigFigure() {
        return bigFigure;
    }

    public double getPips() {
        return pips;
    }

    public double getFractionalPips() {
        return fractionalPips;
    }

    public String getRateDisplay() {
        return rateDisplay;
    }

    public String getBigFigureDisplay() {
        return bigFigureDisplay;
    }

    public String getPipsDisplay() {
        return pipsDisplay;
    }

    public String getFractionalPipsDisplay() {
        return fractionalPipsDisplay;
    }

    public double getOnePipValue() {
        return onePipValue;
    }

    @Override
    public String toString() {
        return "RateProperties{" +
                "rate=" + rate +
                ", bigFigure=" + bigFigure +
                ", pips=" + pips +
                ", fractionalPips=" + fractionalPips +
                ", onePipValue=" + onePipValue +
                ", rateDisplay='" + rateDisplay + '\'' +
                ", bigFigureDisplay='" + bigFigureDisplay + '\'' +
                ", pipsDisplay='" + pipsDisplay + '\'' +
                ", fractionalPipsDisplay='" + fractionalPipsDisplay + '\'' +
                '}';
    }
}
