import com.dhpn.controller.LoginController;
import com.dhpn.controller.RegistrationController;
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
public class RegistrationControllerTest {

    @InjectMocks
    private RegistrationController registrationController;
    private MockMvc mockMvcRegistration;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        mockMvcRegistration = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void test() throws Exception{


        mockMvcRegistration.perform(post("/register").contentType(MediaType.APPLICATION_JSON).
                content("{\"emailId\":\"a\",\"password\":\"\"}"))
                .andDo(print());
    }
}
