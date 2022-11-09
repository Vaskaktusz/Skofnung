package application.service;

import application.entity.skofnung.database.Address;
import application.entity.skofnung.database.Source;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public final class RestTemplate {

    public String httpPost(Source source, String contextPath) {
        HttpPost request = new HttpPost(source.getLocation().concat(contextPath));
        request.setHeader(HttpHeaders.AUTHORIZATION, getBasicAuthCode(source));
        request.setHeader(HttpHeaders.CONTENT_TYPE, "text/html");
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
        return null;
    }
}