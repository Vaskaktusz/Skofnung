package application.service;

import application.entity.skofnung.database.Address;
import application.entity.skofnung.database.Bucket;
import application.entity.skofnung.database.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public final class RestTemplate {
    @Autowired
    private ConnectionManager connectionManager;

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
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate(connectionManager.getRequestFactory());
        restTemplate.getMessageConverters().addAll(connectionManager.getMessageConverters());
        ResponseEntity<K> responseEntity = restTemplate.exchange(
                address.getLocation().concat(contextPath),
                method,
                new HttpEntity<>(body, connectionManager.getHttpHeaders(address.getUsername(), address.getPassword(), new HashMap<>())),
                responseType);
        return responseEntity.getBody();
    }
}