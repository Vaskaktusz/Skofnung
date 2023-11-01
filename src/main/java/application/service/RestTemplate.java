package application.service;

import application.configuration.HttpClient;
import application.entity.skofnung.database.Address;
import application.entity.skofnung.database.Bucket;
import application.entity.skofnung.database.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
 import org.springframework.stereotype.Service;

@Service
public final class RestTemplate {
    @Autowired
    private HttpClient httpClient;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

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
                .basicAuthentication(address.getUsername(), address.getPassword())
                .additionalMessageConverters(httpClient.getMessageConverters())
                .requestFactory(() -> httpClient.getRequestFactory())
                .build()
                .exchange(
                        address.getLocation().concat(contextPath),
                        method,
                        new HttpEntity<>(body),
                        responseType)
                .getBody();
    }
}