package application.controller;

import application.util.Configuration;
import application.util.Payload;
import org.hamcrest.Matchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class DeployControllerTest {
    String save(MockMvc mockMvc, Configuration configuration, String script) throws Exception {
        return mockMvc.perform(Payload.DEPLOYS_SAVE.getRequest(Payload.buildSource(configuration, script, null, null)))
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(Matchers.matchesPattern("[a-z0-9]{32}")))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}