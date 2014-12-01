package prafulmantale.praful.com.yaym.models;

/**
 * Created by prafulmantale on 11/29/14.
 */
public class VolumeChartProperties {

    private static final String TAG = VolumeChartProperties.class.getSimpleName();

    private static final int ONE_MILLION = 1000000;
    private static final int HUNDRED_THOUSAND = 100000;

    private double minVolume;
    private double maxVolume;


    private VolumeChartProperties(){

    }

    public static VolumeChartProperties newInstance(double inputMinVolume, double inputMaxVolume){

        VolumeChartProperties props = new VolumeChartProperties();

        double maxVolumeScaled = inputMaxVolume/ ONE_MILLION;
        if(maxVolumeScaled < ONE_MILLION){
            maxVolumeScaled = inputMaxVolume/HUNDRED_THOUSAND;
        }
        else{
        }
        return props;
    }
}
