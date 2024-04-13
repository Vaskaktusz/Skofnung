package application.controller;

import application.util.Payload;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeployControllerTest {
    String deploy(MockMvc mockMvc, String script) throws Exception {
        return mockMvc.perform(Payload.DEPLOY.getRequest(Payload.buildSource(script, null, null)))
                .andExpect(jsonPath("$").value(matchesPattern("[a-z0-9]{32}")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}