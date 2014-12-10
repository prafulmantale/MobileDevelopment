package prafulmantale.praful.com.instagramviewer.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by prafulmantale on 9/12/14.
 */
public class UserDetails implements Serializable {

    private String id;
    private String username;
    private String fullname;
    private String profilePictureUrl;
    private String website;
    private String bio;
    private SocialDetails socialDetails;

    public UserDetails() {
        website = "";
        bio = "";

        socialDetails = null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public SocialDetails getSocialDetails() {
        return socialDetails;
    }

    public void setSocialDetails(SocialDetails socialDetails) {
        this.socialDetails = socialDetails;
    }

    public boolean isValid(){

        if(username == null || username.isEmpty() || id == null || id.isEmpty()){
            return false;
        }

        return true;
    }

    public static UserDetails fromJSON(JSONObject jsonObject, String jsonTag){

        UserDetails userDetails = new UserDetails();

        try {
            JSONObject userObject = jsonObject.getJSONObject(jsonTag);

            userDetails.id = userObject.getString("id");
            userDetails.username = userObject.getString("username");
            userDetails.profilePictureUrl = userObject.getString("profile_picture");
            userDetails.fullname = userObject.getString("full_name");

            try {
                userDetails.website = userObject.getString("website");
                userDetails.bio = userObject.getString("bio");
            }
            finally {
                //Optional fields
            }

            try{
                userDetails.socialDetails = SocialDetails.fromJSON(userObject.getJSONObject("counts"));
            }
            finally {

            }
        }
        catch (JSONException ex){

        }
        return userDetails;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", website='" + website + '\'' +
                ", bio='" + bio + '\'' +
                ", Social Details='" + socialDetails + '\'' +
                '}';
    }
}


