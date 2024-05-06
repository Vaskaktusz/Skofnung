package application.repository;

import application.entity.database.Source;
import application.service.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeployRepository {
    private final RestTemplate restTemplate;

    public String deploy(Source source) {
        return restTemplate.httpPut(source, "/api/deploy");
    }
}