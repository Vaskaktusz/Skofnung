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
        Payload.setConfiguration(configuration);
        HttpHeaders httpHeaders = new HttpHeaders();
        Payload.setHttpHeaders(httpHeaders);
        String username = "admin";
        String password = "admin";
        new UserDetailsControllerTest(mockMvc).save(username, "{noop}".concat(password));
        httpHeaders.setBasicAuth(username, password);
    }
}