package application.repository;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "gungnir,test")
public class ProgramRepositoryTest extends BaseTest {
    @Test
    void save() throws Exception {
        String location = mockMvc.perform(Payload.PROGRAMS_POST.getRequest(Payload.buildProgram("script", "file", "upload")))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader("Location");
        int index = location.lastIndexOf("/");
        String id = location.substring(index + 1);
        programs(id);
        delete(id);
    }

    private void delete(String id) throws Exception {
        mockMvc.perform(Payload.PROGRAMS_DELETE.getRequest(id))
                .andExpect(status().isNoContent());
    }

    private void programs(String id) throws Exception {
        mockMvc.perform(Payload.PROGRAMS_GET.getRequest(id))
                .andExpect(jsonPath("$.code").value("script"))
                .andExpect(jsonPath("$.file").value("file"))
                .andExpect(jsonPath("$.folder").value("upload"))
                .andExpect(jsonPath("$.location").value(configuration.getLocation()))
                .andExpect(jsonPath("$.password").value(configuration.getPassword()))
                .andExpect(jsonPath("$.username").value(configuration.getUsername()))
                .andExpect(jsonPath("$._links.self.href").value(matchesPattern("[a-z0-9.:/]+".concat(id))))
                .andExpect(status().isOk());
    }
}