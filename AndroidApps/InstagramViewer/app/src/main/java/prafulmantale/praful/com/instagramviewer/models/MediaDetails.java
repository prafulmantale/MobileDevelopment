package prafulmantale.praful.com.instagramviewer.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import prafulmantale.praful.com.instagramviewer.Utils.DateUtils;

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

        userDetails = new UserDetails();
        location = "";
        createdTime = 0;
        caption = null;

        mediaUrl = "";
    }

    private MediaType mediaType;
    private UserDetails userDetails;

    private String location;
    private long createdTime;
    private Caption caption;

    private String mediaUrl;

    private String thumbnailUrl;
    private String lowResolutionUrl;
    private String standardResolutionUrl;


    private Likes likes;
    private Comments comments;

    private boolean iLiked;


    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
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

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
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

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }


    public Comments getComments() {
        return comments;
    }

    public void setComments(Comments comments) {
        this.comments = comments;
    }

    public boolean isiLiked() {
        return iLiked;
    }

    public void setiLiked(boolean iLiked) {
        this.iLiked = iLiked;
    }

    private static MediaDetails fromJSON(JSONObject jsonObject){

        MediaDetails mediaDetails = new MediaDetails();

        try{
            try {
                mediaDetails.mediaType = MediaType.valueOf(jsonObject.getString("type"));
            }
            catch (Exception e){

            }
            mediaDetails.mediaUrl = jsonObject.getString("link");

            mediaDetails.userDetails = UserDetails.fromJSON(jsonObject, "user");
            try {
                mediaDetails.location = jsonObject.getJSONObject("location").getString("name");
            }
            catch (JSONException je){

            }

            mediaDetails.createdTime = jsonObject.getLong("created_time");

            JSONObject imagesObject = jsonObject.getJSONObject("images");
            mediaDetails.thumbnailUrl = imagesObject.getJSONObject("thumbnail").getString("url");
            mediaDetails.lowResolutionUrl = imagesObject.getJSONObject("low_resolution").getString("url");
            mediaDetails.standardResolutionUrl = imagesObject.getJSONObject("standard_resolution").getString("url");

            mediaDetails.likes = Likes.fromJSON(jsonObject);
            mediaDetails.caption = Caption.fromJSON(jsonObject);
            mediaDetails.comments = Comments.fromJSON(jsonObject);


            if((mediaDetails.thumbnailUrl == null || mediaDetails.thumbnailUrl.isEmpty()) ||
                    mediaDetails.lowResolutionUrl == null || mediaDetails.lowResolutionUrl.isEmpty() ||
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


    public String getElapsedTime(){
        return DateUtils.getElapsedDisplayTime(createdTime);
    }

    @Override
    public String toString() {
        return "MediaDetails{" +
                "mediaType=" + mediaType +
                ", username='" + userDetails.getUsername() + '\'' +
                ", profilePictureUrl='" + userDetails.getProfilePictureUrl() + '\'' +
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
