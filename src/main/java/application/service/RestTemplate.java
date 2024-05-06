package application.service;

import application.configuration.HttpClient;
import application.entity.database.Address;
import application.entity.database.Bucket;
import application.entity.database.Source;
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

    public String httpPut(Source source, String contextPath) {
        return exchange(source, contextPath, String.class, HttpMethod.PUT, source.getScript());
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