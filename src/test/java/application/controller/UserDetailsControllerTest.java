package application.controller;

import application.entity.database.Address;
import application.util.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class UserDetailsControllerTest {
    private final MockMvc mockMvc;

    public void save(String username, String password) throws Exception {
        mockMvc.perform(Payload.REGISTER.getRequest(prepareAddress(username, password)))
                .andExpect(status().isOk());
    }
    
    private Address prepareAddress(String username, String password) {
        Address address = new Address();
        address.setUsername(username);
        address.setPassword(password);
        return address;
    }
}