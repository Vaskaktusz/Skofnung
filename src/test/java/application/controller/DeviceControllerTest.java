package application.controller;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "test")
class DeviceControllerTest extends BaseTest {
    @Test
    void findByAddress() throws Exception {
        mockMvc.perform(Payload.SEARCH.getRequest(Payload.buildAddress()))
                .andExpect(jsonPath("$.device.machine").isNotEmpty())
                .andExpect(jsonPath("$.device.node").isNotEmpty())
                .andExpect(jsonPath("$.device.processor").isNotEmpty())
                .andExpect(jsonPath("$.device.release").isNotEmpty())
                .andExpect(jsonPath("$.device.system").value(matchesPattern("Darwin|Java|Linux|Windows")))
                .andExpect(jsonPath("$.device.version").isNotEmpty())
                .andExpect(jsonPath("$.system.author").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.system.name").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.system.version").value(matchesPattern("[1-9](\\.[0-9]+){2}")))
                .andExpect(status().isOk());
    }

    @Test
    void health() throws Exception {
        mockMvc.perform(Payload.HEALTH.getRequest(Payload.buildAddress()))
                .andExpect(status().isOk());
    }
}