package prafulmantale.praful.com.imagefinder.enums;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Supported Image sizes
 */
public enum  ImageSize {

    none(0),
    icon(1),
    small(2),
    xxlarge(3),
    huge(4);


    private int value;

    ImageSize(int val){
        this.value = val;
    }

    public int getValue(){
        return value;
    }
}
