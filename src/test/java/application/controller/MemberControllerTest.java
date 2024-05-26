package application.controller;

import application.entity.database.Address;
import application.util.Payload;
import lombok.RequiredArgsConstructor;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class MemberControllerTest {
    private final MockMvc mockMvc;

    public void save(String username, String password) throws Exception {
        mockMvc.perform(Payload.MEMBERS_PUT.getRequest(prepareAddress(username, password)));
    }
    
    private Address prepareAddress(String username, String password) {
        Address address = new Address();
        address.setUsername(username);
        address.setPassword(password);
        return address;
    }
}