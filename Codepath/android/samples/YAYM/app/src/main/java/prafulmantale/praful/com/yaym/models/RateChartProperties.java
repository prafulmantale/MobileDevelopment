package prafulmantale.praful.com.yaym.models;

/**
 * Created by prafulmantale on 11/28/14.
 */
public class RateChartProperties {

    private static final String TAG = RateChartProperties.class.getSimpleName();

    private double minRate;
    private double maxRate;
    private int steps;

    private RateChartProperties(double minRate, double maxRate){
        this.minRate = minRate;
        this.maxRate = maxRate;
        steps = 0;
    }

    public static RateChartProperties newInstance(String ccyPair, double minRate, double maxRate){
        RateChartProperties properties = new RateChartProperties (minRate, maxRate);

        properties.update(ccyPair);

        return properties;
    }

    private void update(String ccyPair){

        RateProperties rpMin = RateProperties.newInstance(ccyPair, minRate);

        double newMinRate = rpMin.getBigFigure();
        double prevMinRate = newMinRate;
        while(newMinRate < minRate){
            prevMinRate = newMinRate;
            newMinRate += 25 * rpMin.getOnePipValue();
        }

        minRate = prevMinRate;

        RateProperties rpMax = RateProperties.newInstance(ccyPair, maxRate);

        double newMaxRate = rpMax.getBigFigure();

        while(newMaxRate < maxRate){
            newMaxRate += 25 * rpMax.getOnePipValue();
        }

        maxRate = newMaxRate;

        steps = (int)((maxRate - minRate)/(25 * rpMax.getOnePipValue()));

    }

    public double getMinRate() {
        return minRate;
    }

    public double getMaxRate() {
        return maxRate;
    }

    public int getSteps() {
        return steps;
    }
}
