package application.repository;

import application.entity.database.Script;
import application.service.RestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeployRepository {
    private final RestTemplate restTemplate;

    public String deploy(Script script) {
        return restTemplate.httpPut(script, "/api/deploy");
    }
}