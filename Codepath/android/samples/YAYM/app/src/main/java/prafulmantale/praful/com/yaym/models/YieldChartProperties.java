package prafulmantale.praful.com.yaym.models;

/**
 * Created by prafulmantale on 11/29/14.
 */
public class YieldChartProperties {

    private static final String TAG = YieldChartProperties.class.getSimpleName();

    private double minYield;
    private double maxYield;

    private YieldChartProperties(){

    }

    public static YieldChartProperties newInstance(double inputMinYield, double inputMaxYield){
        YieldChartProperties props = new YieldChartProperties();

        return props;
    }


}
