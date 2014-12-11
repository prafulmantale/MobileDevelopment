package prafulmantale.praful.com.yaym.models;

import java.math.BigDecimal;

import prafulmantale.praful.com.yaym.caches.RefDataCache;

/**
 * Created by prafulmantale on 11/28/14.
 */
public class RateChartProperties {

    private static final String TAG = RateChartProperties.class.getSimpleName();

    private double minRate;
    private double maxRate;
    private final int pipStep = 5;
    private String [] scale;

    private RateChartProperties(double minRate, double maxRate){
        this.minRate = minRate;
        this.maxRate = maxRate;
        scale = new String[4];
    }

    public static RateChartProperties newInstance(String ccyPair, double minRate, double maxRate){
        RateChartProperties properties = new RateChartProperties (minRate, maxRate);

        properties.update(ccyPair);

        return properties;
    }

    private void update(String ccyPair){

        RateProperties rpMin = RateProperties.newInstance(ccyPair, minRate);

        double newMinRate = rpMin.getBigFigure();
        while(newMinRate < minRate){
            newMinRate += 25 * rpMin.getOnePipValue();
        }

        while (newMinRate > minRate){
            newMinRate = newMinRate - (pipStep * rpMin.getOnePipValue());
        }
        if(newMinRate == minRate){
            newMinRate = newMinRate - (5 * rpMin.getOnePipValue());
        }
        minRate = newMinRate;

        double newMaxRate = minRate;

        int counter = 0;
        double pipMultiplier = 0;
        while(newMaxRate < maxRate){
            pipMultiplier = ((25 + (pipStep * counter))* rpMin.getOnePipValue());
            newMaxRate = minRate +  pipMultiplier * 3;
            counter ++;
        }

        if(newMaxRate == maxRate){
            newMaxRate = newMaxRate + (5 * rpMin.getOnePipValue());
        }
        maxRate = newMaxRate;

        ReferenceData refData = RefDataCache.getInstance().getReferenceData(ccyPair);
        BigDecimal bmin = new BigDecimal(minRate).setScale(refData.getSpotPrecision(), BigDecimal.ROUND_HALF_UP);
        BigDecimal bmax = new BigDecimal(maxRate).setScale(refData.getSpotPrecision(), BigDecimal.ROUND_HALF_UP);

        minRate = bmin.doubleValue();
        maxRate = bmax.doubleValue();

        String dispRate = RateProperties.newInstance(ccyPair, minRate).getRateDisplay();
        scale[0] = dispRate.substring(0, dispRate.length() - 1);
        dispRate = RateProperties.newInstance(ccyPair, minRate + pipMultiplier).getRateDisplay();
        scale[1] = dispRate.substring(0, dispRate.length() - 1);
        dispRate = RateProperties.newInstance(ccyPair, minRate + (2*pipMultiplier)).getRateDisplay();
        scale[2] = dispRate.substring(0, dispRate.length() - 1);
        dispRate = RateProperties.newInstance(ccyPair, maxRate).getRateDisplay();
        scale[3] = dispRate.substring(0, dispRate.length() - 1);
    }

    public double getMinRate() {
        return minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public String[] getScale() {
        return scale;
    }
}
