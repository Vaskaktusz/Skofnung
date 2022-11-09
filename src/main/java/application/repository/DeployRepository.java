package application.repository;

import application.entity.skofnung.database.Source;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

@Repository
public class DeployRepository {
    @Autowired
    private RestTemplate restTemplate;

    public void save(Source source) {
        // TODO: The actual file is not yet passed from the request to the call.
        restTemplate.exchange(source, Void.class, HttpMethod.PUT, "/api/deploy");
    }
}