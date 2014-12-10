package prafulmantale.praful.com.yaym.models;

import prafulmantale.praful.com.yaym.helpers.AppConstants;

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

        double maxVolumeScaled = inputMaxVolume/ AppConstants.ONE_MILLION;
        if(maxVolumeScaled < AppConstants.ONE_MILLION){
            maxVolumeScaled = inputMaxVolume/AppConstants.HUNDRED_THOUSAND;
        }
        else{
        }
        return props;
    }
}
