package prafulmantale.praful.com.imagefinder.models;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/19/14.
 */

public class SearchResultClass implements Serializable{

    private String title;
    private String tbUrl;
    private long width;
    private long height;
    private String imageId;
    private long tbWidth;
    private long tbHeight;
    private String unEscapedUrl;
    private String url;
    private String visibleUrl;
    private String titleNoFormatting;
    private String originalContextUrl;
    private String content;
    private String contentNoFormatting;

    public SearchResultClass() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTbUrl() {
        return tbUrl;
    }

    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public long getTbWidth() {
        return tbWidth;
    }

    public void setTbWidth(long tbWidth) {
        this.tbWidth = tbWidth;
    }

    public long getTbHeight() {
        return tbHeight;
    }

    public void setTbHeight(long tbHeight) {
        this.tbHeight = tbHeight;
    }

    public String getUnEscapedUrl() {
        return unEscapedUrl;
    }

    public void setUnEscapedUrl(String unEscapedUrl) {
        this.unEscapedUrl = unEscapedUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVisibleUrl() {
        return visibleUrl;
    }

    public void setVisibleUrl(String visibleUrl) {
        this.visibleUrl = visibleUrl;
    }

    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNoFormatting() {
        return contentNoFormatting;
    }

    public void setContentNoFormatting(String contentNoFormatting) {
        this.contentNoFormatting = contentNoFormatting;
    }

    public static SearchResultClass fromJSON(JSONObject jsonObject){

        SearchResultClass searchResultClass = new SearchResultClass();


        return searchResultClass;
    }
}
