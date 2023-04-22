package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "test")
@SpringBootTest
class GungnirControllerTest {
    @Autowired
    private Configuration configuration;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void findByAddress() throws Exception {
        mockMvc.perform(Payload.GUNGNIRS_SEARCH_FINDBYADDRESS.getRequest(Payload.buildAddress(configuration)))
                .andExpect(jsonPath("$.details[*].rule").value(everyItem(matchesPattern("/[a-z]+(/([a-z]+|<[a-z]+>))*"))))
                .andExpect(jsonPath("$.details[*].endpoint").value(everyItem(matchesPattern("(_[a-z]+)+"))))
                .andExpect(jsonPath("$.details[*].view_func").value(everyItem(matchesPattern("(_[a-z]+)+"))))
                .andExpect(jsonPath("$.details[*].methods[*]").value(everyItem(matchesPattern("GET|PUT|DELETE"))))
                .andExpect(jsonPath("$.system.author").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.system.name").value(matchesPattern("[a-z]+")))
                .andExpect(jsonPath("$.system.version").value(matchesPattern("[1-9](\\.[0-9]+){2}")))
                .andExpect(jsonPath("$.device.system").value(matchesPattern("Darwin|Java|Linux|Windows")))
                .andExpect(jsonPath("$.device.node").isNotEmpty())
                .andExpect(jsonPath("$.device.release").isNotEmpty())
                .andExpect(jsonPath("$.device.version").isNotEmpty())
                .andExpect(jsonPath("$.device.machine").isNotEmpty())
                .andExpect(jsonPath("$.device.processor").isNotEmpty())
                .andExpect(status().isOk());
    }

    @Test
    void health() throws Exception {
        mockMvc.perform(Payload.HEALTH.getRequest(Payload.buildAddress(configuration)))
                .andExpect(status().isOk());
    }
}