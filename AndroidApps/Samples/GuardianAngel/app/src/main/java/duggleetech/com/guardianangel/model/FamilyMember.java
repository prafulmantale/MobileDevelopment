package duggleetech.com.guardianangel.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 3/16/15.
 */
@Table(name = "family")
public class FamilyMember extends Model {

    @Column(name = "fn", index = true)
    @JsonProperty("fn")
    private String firstName;

    @Column(name = "ln")
    @JsonProperty("ln")
    private String lastName;

    @Column(name = "nn", index = true)
    @JsonProperty("nn")
    private String nickName;

    @Column(name = "rel")
    @JsonProperty("rel")
    private String relation;

    @Column(name = "mob")
    @JsonProperty("mob")
    private String mobileNumber;

    @Column(name = "email")
    @JsonProperty("email")
    private String emailId;

    @Column(name = "img")
    @JsonProperty("img")
    private String image;

    public FamilyMember() {

        super();

        firstName = "";
        lastName = "";
        nickName = "";
        relation = "";
        mobileNumber = "";
        emailId = "";
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
