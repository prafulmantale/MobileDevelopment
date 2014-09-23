package prafulmantale.praful.com.imagefinder.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 9/19/14.
 */

public class SearchResult implements Serializable{

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

    public SearchResult() {
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

    private static SearchResult fromJSONObject(JSONObject jsonObject){

        SearchResult searchResult = new SearchResult();

        try {

            searchResult.tbUrl = jsonObject.getString("tbUrl");
            searchResult.url = jsonObject.getString("url");
            searchResult.title = jsonObject.getString("title");

        }
        catch (JSONException ex){
            return null;
        }

        return searchResult;
    }

    public static List<SearchResult> fromJSON(JSONObject jsonObject){

        List<SearchResult> list = new ArrayList<SearchResult>();
        try {
            JSONObject responseData = jsonObject.getJSONObject("responseData");
            JSONArray resultsArray = responseData.getJSONArray("results");

            for(int i = 0; i < resultsArray.length(); i ++){

                SearchResult searchResult = null;

                try {
                    JSONObject obj = resultsArray.getJSONObject(i);

                    if (obj == null) {
                        continue;
                    }

                    searchResult = fromJSONObject(obj);
                }
                catch (JSONException e1){
                    continue;
                }

                list.add(searchResult);
            }
        }
        catch (JSONException ex){

        }

        return list;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", tbUrl='" + tbUrl + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", imageId='" + imageId + '\'' +
                ", tbWidth=" + tbWidth +
                ", tbHeight=" + tbHeight +
                ", unEscapedUrl='" + unEscapedUrl + '\'' +
                ", url='" + url + '\'' +
                ", visibleUrl='" + visibleUrl + '\'' +
                ", titleNoFormatting='" + titleNoFormatting + '\'' +
                ", originalContextUrl='" + originalContextUrl + '\'' +
                ", content='" + content + '\'' +
                ", contentNoFormatting='" + contentNoFormatting + '\'' +
                '}';
    }
}
