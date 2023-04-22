package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeployControllerTest {
    String save(MockMvc mockMvc, Configuration configuration, String script) throws Exception {
        return mockMvc.perform(Payload.DEPLOYS_SAVE.getRequest(Payload.buildSource(configuration, script, null, null)))
                .andExpect(jsonPath("$").value(matchesPattern("[a-z0-9]{32}")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}