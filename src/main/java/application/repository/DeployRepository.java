package application.repository;

import application.entity.skofnung.database.Source;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DeployRepository {
    @Autowired
    private RestTemplate restTemplate;

    public void save(Source source) {
        restTemplate.httpPost(source, "/api/deploy");
    }
}