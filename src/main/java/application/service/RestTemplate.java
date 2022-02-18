package application.service;

import application.entity.skofnung.database.Address;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public final class RestTemplate {
    public <K> K exchange(Address address, Class<K> clazz, HttpMethod method, String contextPath) {
        org.springframework.web.client.RestTemplate restTemplate = new org.springframework.web.client.RestTemplate();
        restTemplate.getMessageConverters().add(setSupportedMediaTypes());
        ResponseEntity<K> responseEntity = restTemplate.exchange(
                address.getLocation().concat(contextPath),
                method,
                setBasicAuth(address.getUsername(), address.getPassword()),
                clazz);
        return responseEntity.getBody();
    }

    private HttpEntity<String> setBasicAuth(String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        return new HttpEntity<>("body", httpHeaders);
    }

    private AbstractGenericHttpMessageConverter<Object> setSupportedMediaTypes() {
        AbstractGenericHttpMessageConverter<Object> httpMessageConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        return httpMessageConverter;
    }
}
