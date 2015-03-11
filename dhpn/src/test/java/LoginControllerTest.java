import com.dhpn.controller.LoginController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by prafulmantale on 3/1/15.
 */
public class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;
    private MockMvc mockMvcLogin;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvcLogin = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void test() throws Exception{


        mockMvcLogin.perform(post("/login").contentType(MediaType.APPLICATION_JSON).
                content("{\"emailID\":\"a\",\"password\":\"\"}"))
                .andDo(print());
    }
}
