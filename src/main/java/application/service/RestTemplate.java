package application.service;

import application.configuration.HttpClient;
import application.entity.database.Address;
import application.entity.database.Bucket;
import application.entity.database.Program;
import application.entity.database.Script;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public final class RestTemplate {
    private final HttpClient httpClient;
    private final RestTemplateBuilder restTemplateBuilder;

    public void httpDelete(Bucket bucket, String contextPath) {
        exchange(bucket, contextPath, Void.class, HttpMethod.DELETE, null);
    }

    public <K> K httpGet(Address address, String contextPath, Class<K> responseType) {
        return exchange(address, contextPath, responseType, HttpMethod.GET, null);
    }

    public void httpPut(Program program, String contextPath) {
        exchange(program, contextPath, Void.class, HttpMethod.PUT, program.getCode());
    }

    public String httpPut(Script script, String contextPath) {
        return exchange(script, contextPath, String.class, HttpMethod.PUT, script.getCode());
    }

    private <K> K exchange(Address address, String contextPath, Class<K> responseType, HttpMethod method, String body) {
        return restTemplateBuilder
                .additionalMessageConverters(httpClient.getMessageConverters())
                .basicAuthentication(address.getUsername(), address.getPassword())
                .requestFactory(httpClient::getRequestFactory)
                .build()
                .exchange(
                        address.getLocation().concat(contextPath),
                        method,
                        new HttpEntity<>(body),
                        responseType)
                .getBody();
    }
}