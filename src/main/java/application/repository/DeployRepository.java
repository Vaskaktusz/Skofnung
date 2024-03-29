package application.repository;

import application.entity.database.Source;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeployRepository {
    @Autowired
    private RestTemplate restTemplate;

    public String deploy(Source source) {
        return restTemplate.httpPut(source, "/api/deploy");
    }
}