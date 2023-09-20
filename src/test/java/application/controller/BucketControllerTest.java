package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
@SpringBootTest
class BucketControllerTest {
    private final MockMvc mockMvc;

    @Autowired
    public BucketControllerTest(Configuration configuration, MockMvc mockMvc) {
        Payload.setConfiguration(configuration);
        this.mockMvc = mockMvc;
    }

    @Test
    void buckets() throws Exception {
        mockMvc.perform(Payload.BUCKETS_SAVE.getRequest(Payload.buildSource("script", "file", "upload")))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.FIND_BY_BUCKET.getRequest(Payload.buildBucket("file", "upload")))
                .andExpect(jsonPath("$.file._embedded").value("script"))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket("file", "upload")))
                .andExpect(status().isOk());
    }

    @Test
    void save() throws Exception {
        DeployControllerTest deployControllerTest = new DeployControllerTest();
        String name = deployControllerTest.save(mockMvc, "echo script");
        mockMvc.perform(Payload.FIND_BY_BUCKET.getRequest(Payload.buildBucket(name, "logger")))
                .andExpect(jsonPath(String.format("$.%s._embedded", name)).value("script\n"))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(name, "deploy")))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(name, "logger")))
                .andExpect(status().isOk());
    }
}