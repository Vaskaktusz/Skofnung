package application.service;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public final class HttpClient {
    public HttpHeaders getHttpHeaders(String username, String password, Map<String, String> values) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password);
        httpHeaders.setAll(values);
        return httpHeaders;
    }

    public List<HttpMessageConverter<Object>> getMessageConverters() {
        AbstractHttpMessageConverter<Object> httpMessageConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        return Collections.singletonList(httpMessageConverter);
    }

    public ClientHttpRequestFactory getRequestFactory() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(getHttpClient());
        return requestFactory;
    }

    private org.apache.hc.client5.http.classic.HttpClient getHttpClient() {
        try {
            return HttpClients.custom()
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                            .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                                    .setSslContext(SSLContexts.custom().loadTrustMaterial(null, (chain, authType) -> true).build())
                                    .setHostnameVerifier((hostname, session) -> true).build())
                            .build())
                    .evictExpiredConnections()
                    .build();
        } catch (GeneralSecurityException gse) {
            return HttpClients.createDefault();
        }
    }
}