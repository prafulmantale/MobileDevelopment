package prafulmantale.praful.com.imagefinder.enums;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Supported Image colors in the finder
 */
public enum  ImageColor {

    none(0),
    black(1),
    blue(2),
    brown(3),
    gray(4),
    green(5),
    orange(6),
    pink(7),
    purple(8),
    red(9),
    teal(10),
    white(11),
    yellow(12);

    private int value;

    ImageColor(int val){
        this.value = val;
    }

    public int getValue(){
        return value;
    }
}
