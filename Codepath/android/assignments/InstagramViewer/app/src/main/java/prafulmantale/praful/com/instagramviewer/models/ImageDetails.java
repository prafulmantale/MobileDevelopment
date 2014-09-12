package prafulmantale.praful.com.instagramviewer.models;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class ImageDetails implements Serializable {

    public enum ImageTypes{
        low_resolution,
        thumbnail,
        standard_resolution
    }

    public ImageDetails() {
        imageType = ImageTypes.low_resolution;
        url = "";
        width = 0;
        height = 0;
    }

    private ImageTypes imageType;
    private String url;
    private long width;
    private long height;

    public ImageTypes getImageType() {
        return imageType;
    }

    public void setImageType(ImageTypes imageType) {
        this.imageType = imageType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }
}
