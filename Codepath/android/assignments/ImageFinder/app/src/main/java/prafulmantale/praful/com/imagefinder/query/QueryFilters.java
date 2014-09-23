package prafulmantale.praful.com.imagefinder.query;

import prafulmantale.praful.com.imagefinder.enums.ImageColor;
import prafulmantale.praful.com.imagefinder.enums.ImageSize;
import prafulmantale.praful.com.imagefinder.enums.ImageType;

/**
 * Created by prafulmantale on 9/22/14.
 *
 * Container class for the Query filters
 */
public class QueryFilters {

    private ImageColor color;
    private ImageType type;
    private ImageSize size;
    private String domain;

    public QueryFilters() {
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
