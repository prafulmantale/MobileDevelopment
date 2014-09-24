package prafulmantale.praful.com.imagefinder.query;

import com.loopj.android.http.RequestParams;

import prafulmantale.praful.com.imagefinder.enums.ImageColor;
import prafulmantale.praful.com.imagefinder.enums.ImageSize;
import prafulmantale.praful.com.imagefinder.enums.ImageType;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Container class for the Query filters
 */
public class QueryFilters {

    private static final String KEY_IMAGE_COLOR = "imgcolor";
    private static final String KEY_IMAGE_SIZE = "imgsz";
    private static final String KEY_IMAGE_TYPE = "imgtype";
    private static final String KEY_DOMAIN = "as_sitesearch";

    private ImageColor color;
    private ImageType type;
    private ImageSize size;
    private String domain;

    public QueryFilters() {
        color = ImageColor.none;
        type = ImageType.none;
        size = ImageSize.none;
        domain = "";
    }


    public ImageColor getColor() {
        return color;
    }

    public void setColor(ImageColor color) {
        this.color = color;
    }

    public ImageType getType() {
        return type;
    }

    public void setType(ImageType type) {
        this.type = type;
    }

    public ImageSize getSize() {
        return size;
    }

    public void setSize(ImageSize size) {
        this.size = size;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    protected void populateRequestParameters(RequestParams requestParams){

        if(requestParams == null){
            requestParams = new RequestParams();
        }

        if(color != ImageColor.none){
            requestParams.put(KEY_IMAGE_COLOR, color.name());
        }

        if(size != ImageSize.none){
            requestParams.put(KEY_IMAGE_SIZE, size.name());
        }

        if(type != ImageType.none){
            requestParams.put(KEY_IMAGE_TYPE, type.name());
        }

        if(color != ImageColor.none){
            if(domain!= null && domain.trim().isEmpty() == false){
                requestParams.put(KEY_DOMAIN, domain.trim());
            }
        }
    }

    @Override
    public String toString() {
        return "QueryFilters{" +
                "color=" + color +
                ", type=" + type +
                ", size=" + size +
                ", domain='" + domain + '\'' +
                '}';
    }
}
