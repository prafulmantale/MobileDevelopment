package prafulmantale.praful.com.instagramviewer.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class MediaDetails implements Serializable{

    public enum MediaType{
        Image,
        Video
    }

    public MediaDetails() {
        mediaType = MediaType.Image;
        username = "";
        profilePictureUrl = "";
        location = "";
        createdTime = 0;
        caption = "";

        mediaUrl = "";

//        likes = new Likes();
//        comments = new Comments();
    }

    private MediaType mediaType;
    private String username;
    private String profilePictureUrl;
    private String location;
    private long createdTime;
    private String caption;

    private String mediaUrl;

    private String thumbnailUrl;
    private String lowResolutionUrl;
    private String standardResolutionUrl;


//    private Likes likes;
//    private Comments comments;


    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getLowResolutionUrl() {
        return lowResolutionUrl;
    }

    public void setLowResolutionUrl(String lowResolutionUrl) {
        this.lowResolutionUrl = lowResolutionUrl;
    }

    public String getStandardResolutionUrl() {
        return standardResolutionUrl;
    }

    public void setStandardResolutionUrl(String standardResolutionUrl) {
        this.standardResolutionUrl = standardResolutionUrl;
    }

    //    public Likes getLikes() {
//        return likes;
//    }
//
//    public void setLikes(Likes likes) {
//        this.likes = likes;
//    }
//
//    public Comments getComments() {
//        return comments;
//    }
//
//    public void setComments(Comments comments) {
//        this.comments = comments;
//    }

    private static MediaDetails fromJSON(JSONObject jsonObject){

        MediaDetails mediaDetails = new MediaDetails();

        try{
            try {
                mediaDetails.mediaType = MediaType.valueOf(jsonObject.getString("type"));
            }
            catch (Exception e){

            }
            mediaDetails.mediaUrl = jsonObject.getString("link");

            JSONObject userObject = jsonObject.getJSONObject("user");

            mediaDetails.username = userObject.getString("username");

            mediaDetails.profilePictureUrl = userObject.getString("profile_picture");

            mediaDetails.location = jsonObject.getString("location");

            mediaDetails.createdTime = jsonObject.getLong("created_time");

            JSONObject captionObject = jsonObject.getJSONObject("caption");
            mediaDetails.caption = captionObject.getString("text");

            JSONObject imagesObject = jsonObject.getJSONObject("images");
            mediaDetails.thumbnailUrl = imagesObject.getJSONObject("thumbnail").getString("url");
            mediaDetails.lowResolutionUrl = imagesObject.getJSONObject("low_resolution").getString("url");
            mediaDetails.standardResolutionUrl = imagesObject.getJSONObject("standard_resolution").getString("url");

            if((mediaDetails.thumbnailUrl == null || mediaDetails.thumbnailUrl.isEmpty()) &&
                    mediaDetails.lowResolutionUrl == null || mediaDetails.lowResolutionUrl.isEmpty() &&
                    mediaDetails.standardResolutionUrl == null || mediaDetails.standardResolutionUrl.isEmpty()){
                return null;
            }

        }
        catch (JSONException ex){

        }
        finally {

        }

        return mediaDetails;
    }
    public static List<MediaDetails> fromJSON(JSONArray jsonArray){

        List<MediaDetails> mediaDetailsList = new ArrayList<MediaDetails>();

        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject obj = null;
            try {
                obj = jsonArray.getJSONObject(i);
            }
            catch (JSONException e){
                e.printStackTrace();
            }

            if(obj == null){
                continue;
            }

            MediaDetails mediaDetails = fromJSON(obj);
            //Ignore Video for time being
            if(mediaDetails == null || mediaDetails.getMediaType() == MediaType.Video){
                continue;
            }

            mediaDetailsList.add(mediaDetails);
        }

        return mediaDetailsList;
    }

    @Override
    public String toString() {
        return "MediaDetails{" +
                "mediaType=" + mediaType +
                ", username='" + username + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", location='" + location + '\'' +
                ", createdTime=" + createdTime +
                ", caption='" + caption + '\'' +
                ", mediaUrl='" + mediaUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                ", lowResolutionUrl='" + lowResolutionUrl + '\'' +
                ", standardResolutionUrl='" + standardResolutionUrl + '\'' +
                '}';
    }
}
