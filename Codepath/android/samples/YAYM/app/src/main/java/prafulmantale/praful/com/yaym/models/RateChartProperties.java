package prafulmantale.praful.com.yaym.models;

/**
 * Created by prafulmantale on 11/28/14.
 */
public class RateChartProperties {

    private double minRate;
    private double maxRate;
    private double steps;

    private RateChartProperties(double minRate, double maxRate){
        this.minRate = minRate;
        this.maxRate = maxRate;
        steps = 0;
    }

    public RateChartProperties newInstance(String ccyPair, double minRate, double maxRate){
        RateChartProperties properties = new RateChartProperties (minRate, maxRate);

        properties.update();

        return properties;
    }

    private void update(){

        //Get min big figure
        //while(newMinRate < minRate) add 25 pips
        //while(newMaxRate is not greater than maxRate) add 25 pips
        //steps = (max - min)/25
    }
}
