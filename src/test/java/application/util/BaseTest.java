package application.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class BaseTest {
    @Autowired
    private Configuration configuration;
    @Autowired
    public MockMvc mockMvc;

    @PostConstruct
    private void init() {
        Payload.setConfiguration(configuration);
    }
}