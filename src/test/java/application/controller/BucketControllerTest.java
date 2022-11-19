package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "INTEGRATION_TEST", matches = "(?i)(true)")
@AutoConfigureMockMvc
class BucketControllerTest {
    private final DeployControllerTest deployControllerTest = new DeployControllerTest();
    @Autowired
    private Configuration configuration;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void buckets() throws Exception {
        mockMvc.perform(Payload.BUCKETS_SAVE.getRequest(Payload.buildSource(configuration, "script", "file", "upload")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(Payload.FIND_BY_BUCKET.getRequest(Payload.buildBucket(configuration, "file", "upload")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.file._embedded").value("script"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(configuration, "file", "upload")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void save() throws Exception {
        String name = deployControllerTest.save(mockMvc, configuration, "echo script");
        mockMvc.perform(Payload.FIND_BY_BUCKET.getRequest(Payload.buildBucket(configuration, name, "logger")))
                .andExpect(MockMvcResultMatchers.jsonPath(String.format("$.%s._embedded", name)).value("script\n"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(configuration, name, "deploy")))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(configuration, name, "logger")))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}