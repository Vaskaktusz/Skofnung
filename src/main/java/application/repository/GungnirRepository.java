package application.repository;

import application.entity.gungnir.Gungnir;
import application.entity.gungnir.metadata.Details;
import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import application.entity.skofnung.database.Address;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Repository
public class GungnirRepository {
    public Gungnir findByAddress(Address address) {
        Gungnir gungnir = new Gungnir();
        gungnir.setDetails(bodyToMono(address, Details.class, "/api/detail"));
        gungnir.setDevice(bodyToMono(address, Device.class, "/api/device"));
        gungnir.setSystem(bodyToMono(address, System.class, "/api/system"));
        return gungnir;
    }

    private <K> K bodyToMono(Address address, Class<K> clazz, String contextPath) {
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
