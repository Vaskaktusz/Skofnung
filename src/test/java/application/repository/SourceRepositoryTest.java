package application.repository;

import application.util.BaseTest;
import application.util.Payload;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EnabledIfEnvironmentVariable(named = "SPRING_PROFILES_ACTIVE", matches = "gungnir,test")
public class SourceRepositoryTest extends BaseTest {
    @Test
    void save() throws Exception {
        // TODO: Finish test.
        mockMvc.perform(Payload.SOURCES_POST.getRequest(Payload.buildSource("script", "file", "upload")))
                .andExpect(status().isCreated());
    }
}