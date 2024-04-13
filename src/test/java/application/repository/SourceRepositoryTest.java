package application.repository;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "gungnir,test")
public class SourceRepositoryTest extends BaseTest {a
    @Test
    void save() throws Exception {
        String location = mockMvc.perform(Payload.SOURCES_POST.getRequest(Payload.buildSource("script", "file", "upload")))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
        int index = location.lastIndexOf("/");
        String id = location.substring(index + 1);
        sources(id);
    }

    void sources(String id) throws Exception {
        mockMvc.perform(Payload.SOURCES_GET.getRequest(id))
                .andExpect(jsonPath("$.script").value("script"))
                .andExpect(jsonPath("$.file").value("file"))
                .andExpect(jsonPath("$.folder").value("upload"))
                .andExpect(jsonPath("$.location").value(configuration.getLocation()))
                .andExpect(jsonPath("$.password").value(configuration.getPassword()))
                .andExpect(jsonPath("$.username").value(configuration.getUsername()))
                .andExpect(jsonPath("$._links.self.href").value(matchesPattern("[a-z0-9.:/]+".concat(id))))
                .andExpect(status().isOk());
    }
}