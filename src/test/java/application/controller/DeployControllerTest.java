package application.controller;

import application.util.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
class DeployControllerTest {
    private final MockMvc mockMvc;

    String deploy(String code) throws Exception {
        return mockMvc.perform(Payload.DEPLOY.getRequest(Payload.buildProgram(code, null, null)))
                .andExpect(jsonPath("$").value(matchesPattern("[a-z0-9]{32}")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}