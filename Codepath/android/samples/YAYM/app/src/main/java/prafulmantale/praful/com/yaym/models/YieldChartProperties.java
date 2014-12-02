package prafulmantale.praful.com.yaym.models;

import prafulmantale.praful.com.yaym.helpers.UOMNumber;

/**
 * Created by prafulmantale on 11/29/14.
 */
public class YieldChartProperties {

    private static final String TAG = YieldChartProperties.class.getSimpleName();

    private UOMNumber minYield;
    private UOMNumber maxYield;

    private YieldChartProperties(){

    }

    public static YieldChartProperties newInstance(double inputMinYield, double inputMaxYield){
        YieldChartProperties props = new YieldChartProperties();

        UOMNumber minUN = new UOMNumber(inputMinYield);

        int min = (int)minUN.getFormattedValue();
        if(min % 2 != 0){
            min = min - 1;
        }

        props.minYield = new UOMNumber(min);

        UOMNumber maxUN = new UOMNumber(inputMaxYield);
        int max = (int)maxUN.getFormattedValue() + 1;

        if(max % 2 != 0){
            max = max + 1;
        }


        props.maxYield = new UOMNumber(max);

        return props;
    }

    public UOMNumber getMinYield() {
        return minYield;
    }

    public UOMNumber getMaxYield() {
        return maxYield;
    }
}
