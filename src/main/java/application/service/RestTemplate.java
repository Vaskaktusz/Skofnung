package application.service;

import application.entity.skofnung.database.Address;
import application.entity.skofnung.database.Source;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

@Service
public final class RestTemplate {

    public String httpPost(Source source, String contextPath) {
        HttpPut request = new HttpPut(String.format("http://%s%s", source.getLocation(), contextPath));
        request.setHeader(HttpHeaders.AUTHORIZATION, getBasicAuthCode(source));
        request.setEntity(new StringEntity(source.getScript(), "UTF-8"));
        try {
            HttpClient httpclient = HttpClients.createDefault();
            HttpResponse response = httpclient.execute(request);
            return new String(response.getEntity().getContent().readAllBytes());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    private String getBasicAuthCode(Address address) {
        String auth = String.format("%s:%s", address.getUsername(), address.getPassword());
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.ISO_8859_1));
        return String.format("Basic %s", new String(encodedAuth));
    }

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