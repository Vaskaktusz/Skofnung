package application.facade;

import application.entity.skofnung.database.Address;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class GungnirFacade {
    public <K> K bodyToMono(Address address, Class<K> clazz, String contextPath) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(httpMessageConverterToMono());
        ResponseEntity<K> responseEntity = restTemplate.exchange(
                address.getLocation().concat(contextPath),
                HttpMethod.GET,
                headersToMono(address.getUsername(), address.getPassword()),
                clazz);
        return responseEntity.getBody();
    }

    private HttpEntity<String> headersToMono(String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        return new HttpEntity<>("body", httpHeaders);
    }

    private AbstractGenericHttpMessageConverter<Object> httpMessageConverterToMono() {
        AbstractGenericHttpMessageConverter<Object> httpMessageConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        return httpMessageConverter;
    }
}
