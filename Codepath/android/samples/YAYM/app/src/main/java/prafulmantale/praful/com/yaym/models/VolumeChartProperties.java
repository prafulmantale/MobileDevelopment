package prafulmantale.praful.com.yaym.models;

/**
 * Created by prafulmantale on 11/29/14.
 */
public class VolumeChartProperties {

    private static final String TAG = VolumeChartProperties.class.getSimpleName();

    private double minVolume;
    private double maxVolume;

    private VolumeChartProperties(){

    }

    public static VolumeChartProperties newInstance(double inputMinVolume, double inputMaxVolume){

        VolumeChartProperties props = new VolumeChartProperties();

        return props;
    }
}
