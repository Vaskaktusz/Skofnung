package application.repository;

import application.entity.skofnung.database.Address;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

@Repository
public class DeployRepository {
    @Autowired
    private RestTemplate restTemplate;

    public void save(Address address) {
        // TODO: The actual file is not yet passed from the request to the call.
        restTemplate.exchange(address, Void.class, HttpMethod.PUT, "/api/deploy");
    }
}
