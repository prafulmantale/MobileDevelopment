package duggleetech.com.guardianangel.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by prafulmantale on 3/11/15.
 */
public class LoginRequest {

    @JsonProperty("lid")
    private String loginId;

    @JsonProperty("pwd")
    private String password;


    public LoginRequest() {

//        ObjectMapper mapper = new ObjectMapper();
//        Entity entity = mapper.readValue(jsonString, Entity.class);
//        System.out.println(entity);
//        String json = mapper.writeValueAsString(this);

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
