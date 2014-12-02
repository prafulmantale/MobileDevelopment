package prafulmantale.praful.com.yaym.helpers;

import java.text.DecimalFormat;

import prafulmantale.praful.com.yaym.enums.UOMSymbol;

/**
 * Created by prafulmantale on 12/1/14.
 */
public class UOMNumber {

    private double absoluteValue;
    private double formattedValue;
    private String displayValue;
    private UOMSymbol uomSymbol;
    private DecimalFormat decimalFormat = new DecimalFormat("####.##");

    public UOMNumber(double number){
        absoluteValue = number;
        initialize();
    }

    private void initialize(){
        double tempVal = absoluteValue;
        if(absoluteValue >= AppConstants.ONE_MILLION){
            tempVal = absoluteValue/AppConstants.ONE_MILLION;
            formattedValue = tempVal;
            displayValue = decimalFormat.format(tempVal);
            uomSymbol = UOMSymbol.M;
        }
        else if(absoluteValue >= AppConstants.ONE_THOUSAND){
            tempVal = absoluteValue/AppConstants.ONE_THOUSAND;
            formattedValue = tempVal;
            displayValue = decimalFormat.format(tempVal);
            uomSymbol = UOMSymbol.K;
        }
        else{
            tempVal = absoluteValue/AppConstants.ONE_THOUSAND;
            formattedValue = tempVal;
            displayValue = String.valueOf(tempVal);
            uomSymbol = UOMSymbol.K;
        }

        displayValue = displayValue + uomSymbol.toString();
    }

    public double getAbsoluteValue() {
        return absoluteValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public UOMSymbol getUomSymbol() {
        return uomSymbol;
    }

    public double getFormattedValue() {
        return formattedValue;
    }
}
