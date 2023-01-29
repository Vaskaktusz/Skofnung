package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[*].rule").value(Matchers.everyItem(Matchers.matchesPattern("/[a-z]+(/([a-z]+|<[a-z]+>))*"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[*].endpoint").value(Matchers.everyItem(Matchers.matchesPattern("(_[a-z]+)+"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[*].view_func").value(Matchers.everyItem(Matchers.matchesPattern("(_[a-z]+)+"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.details[*].methods[*]").value(Matchers.everyItem(Matchers.matchesPattern("GET|PUT|DELETE"))))
                .andExpect(MockMvcResultMatchers.jsonPath("$.system.author").value(Matchers.matchesPattern("[a-z]+")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.system.name").value(Matchers.matchesPattern("[a-z]+")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.system.version").value(Matchers.matchesPattern("[1-9](\\.[0-9]+){2}")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.system").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.node").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.release").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.version").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.machine").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.device.processor").isNotEmpty())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void health() throws Exception {
        mockMvc.perform(Payload.HEALTH.getRequest(Payload.buildAddress(configuration)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}