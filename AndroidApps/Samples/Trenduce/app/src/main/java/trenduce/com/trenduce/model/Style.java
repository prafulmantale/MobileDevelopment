package trenduce.com.trenduce.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class Style {

    private String id;

    private String createdBy;

    private String lastUpdated;

    private String title;

    private String imageUrl;

    private String price;

    private String viewCount; // Every time user clicks on this style

    private String likesCount;

    private String commentsCount;

    private List<String> tags;

    public Style() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(String likesCount) {
        this.likesCount = likesCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public static Style fromJSON(JSONObject jsonObject){

        Style style = new Style();

        try{
            style.id = jsonObject.getString("id");
            style.createdBy = jsonObject.getString("createBy");
            style.lastUpdated = jsonObject.getString("lastUpdated");
            style.title = jsonObject.getString("title");
            style.imageUrl = jsonObject.getString("image");
            style.viewCount = jsonObject.getString("viewCount");
            style.likesCount = jsonObject.getString("likesCount");
            style.commentsCount = jsonObject.getString("commentsCount");

            JSONObject priceObj = jsonObject.getJSONObject("Price");
            style.price = priceObj.getString("price");

        }
        catch (JSONException ex){

            style = null;
        }

        return style;
    }

    public static List<Style> fromJSONArray(JSONArray jsonArray){

        List<Style> list = new ArrayList<>();

        if(jsonArray == null || jsonArray.length() == 0){
            return list;
        }

        int len = jsonArray.length();

        for(int i = 0; i < len; i ++){

            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if (jsonObject == null) {
                    continue;
                }

                Style style = fromJSON(jsonObject);

                if (style != null) {
                    list.add(style);
                }
            }
            catch (JSONException jex){

            }
        }

        return list;
    }
}
