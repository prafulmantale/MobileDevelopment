package duggleetech.com.guardianangel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 3/11/15.
 */
public class RegistrationRequest {

    @JsonProperty("fn")
    private String firstName;


    @JsonProperty("ln")
    private String lastName;

    @JsonProperty("lid")
    private String loginId;

    @JsonProperty("pwd")
    private String password;


    public RegistrationRequest() {
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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
