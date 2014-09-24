package prafulmantale.praful.com.imagefinder.enums;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Supported Image types
 */
public enum ImageType {

    none(0),
    face(1),
    photo(2),
    clipart(3),
    lineart(4);

    private int value;

    ImageType(int val){
        this.value = val;
    }

    public int getValue(){
        return value;
    }
}
