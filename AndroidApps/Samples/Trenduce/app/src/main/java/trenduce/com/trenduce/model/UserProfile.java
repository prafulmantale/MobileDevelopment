package trenduce.com.trenduce.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by prafulmantale on 3/7/15.
 */
public class UserProfile {

    private static final String TAG = UserProfile.class.getSimpleName();

    private static UserProfile INSTANCE = new UserProfile();

    private String id;

    private String emailId;
    private String password;

    private String firstName;
    private String lastName;
    private int age;

    private List<String> followers;
    private List<String> following;
    private List<String> conversations;

    private long trenducePoints;

    private String displayCreatedTime;


    private UserProfile() {
    }


    public static UserProfile getInstance(){

        return INSTANCE;
    }

    public void reset(){
        INSTANCE = new UserProfile();
    }

    public void fromJSON(JSONObject jsonObject){

        try {
            id = jsonObject.getString("id");
            emailId = jsonObject.getString("userName");
            firstName = jsonObject.getString("firstName");
            lastName = jsonObject.getString("lastName");
            age = jsonObject.getInt("age");
            trenducePoints = jsonObject.getLong("trenducePoints");



        }
        catch (JSONException ex){

        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }

    public List<String> getFollowing() {
        return following;
    }

    public void setFollowing(List<String> following) {
        this.following = following;
    }

    public List<String> getConversations() {
        return conversations;
    }

    public void setConversations(List<String> conversations) {
        this.conversations = conversations;
    }

    public long getTrenducePoints() {
        return trenducePoints;
    }

    public void setTrenducePoints(long trenducePoints) {
        this.trenducePoints = trenducePoints;
    }

    public String getDisplayCreatedTime() {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime(String displayCreatedTime) {
        this.displayCreatedTime = displayCreatedTime;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id='" + id + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", followers=" + followers +
                ", following=" + following +
                ", conversations=" + conversations +
                ", trenducePoints=" + trenducePoints +
                ", displayCreatedTime='" + displayCreatedTime + '\'' +
                '}';
    }
}
