package application.util;

import application.controller.UserDetailsControllerTest;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class BaseTest {
    @Autowired
    public MockMvc mockMvc;
    @Autowired
    public Configuration configuration;

    @PostConstruct
    private void init() throws Exception {
        HttpHeaders httpHeaders = new HttpHeaders();
        Payload.setConfiguration(configuration);
        Payload.setHttpHeaders(httpHeaders);
        UserDetailsControllerTest userDetailsControllerTest = new UserDetailsControllerTest(mockMvc);
        userDetailsControllerTest.save("admin", "{noop}admin");
        httpHeaders.setBasicAuth("admin", "admin");
    }
}