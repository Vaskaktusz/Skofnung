package application.controller;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "gungnir,test")
class BucketControllerTest extends BaseTest {
    @Test
    void buckets() throws Exception {
        mockMvc.perform(Payload.BUCKETS_PUT.getRequest(Payload.buildSource("script", "file", "upload")))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_SEARCH.getRequest(Payload.buildBucket("file", "upload")))
                .andExpect(jsonPath("$.file._embedded").value("script"))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket("file", "upload")))
                .andExpect(status().isOk());
    }

    @Test
    void deploy() throws Exception {
        DeployControllerTest deployControllerTest = new DeployControllerTest();
        String name = deployControllerTest.deploy(mockMvc, "echo script");
        mockMvc.perform(Payload.BUCKETS_SEARCH.getRequest(Payload.buildBucket(name, "logger")))
                .andExpect(jsonPath(String.format("$.%s._embedded", name)).value("script\n"))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(name, "deploy")))
                .andExpect(status().isOk());
        mockMvc.perform(Payload.BUCKETS_DELETE.getRequest(Payload.buildBucket(name, "logger")))
                .andExpect(status().isOk());
    }
}