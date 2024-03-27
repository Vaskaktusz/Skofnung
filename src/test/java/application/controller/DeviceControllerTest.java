package application.controller;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "test")
class DeviceControllerTest extends BaseTest {
    @Test
    void device() throws Exception {
        mockMvc.perform(Payload.DEVICE.getRequest(Payload.buildAddress()))
                .andExpect(jsonPath("$.machine").isNotEmpty())
                .andExpect(jsonPath("$.node").isNotEmpty())
                .andExpect(jsonPath("$.processor").isNotEmpty())
                .andExpect(jsonPath("$.release").isNotEmpty())
                .andExpect(jsonPath("$.system").value(matchesPattern("Darwin|Java|Linux|Windows")))
                .andExpect(jsonPath("$.version").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void health() throws Exception {
        mockMvc.perform(Payload.HEALTH.getRequest(Payload.buildAddress()))
                .andExpect(status().isOk());
    }

    @Test
    void system() throws Exception {
        mockMvc.perform(Payload.SYSTEM.getRequest(Payload.buildAddress()))
                .andExpect(jsonPath("$.author").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.name").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.version").value(matchesPattern("[1-9](\\.[0-9]+){2}")))
                .andExpect(status().isOk());
    }
}