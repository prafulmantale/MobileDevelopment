package prafulmantale.praful.com.simpleuserlist.models;

/**
 * Created by prafulmantale on 9/11/14.
 */
public class User {

    private String name;
    private String home;
    private String imageUrl;

    public User(String name, String home) {
        this.name = name;
        this.home = home;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //De serializing JSON

    //Persist

    //Presenter

    //Validation
}
