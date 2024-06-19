package application.configuration;

import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;

import java.security.GeneralSecurityException;
import java.util.List;

@Configuration
public class HttpClient {
    public List<HttpMessageConverter<Object>> getMessageConverters() {
        AbstractHttpMessageConverter<Object> httpMessageConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverter.setSupportedMediaTypes(List.of(MediaType.ALL));
        return List.of(httpMessageConverter);
    }

    public ClientHttpRequestFactory getRequestFactory() {
        try {
            return new HttpComponentsClientHttpRequestFactory(HttpClients
                    .custom()
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder
                            .create()
                            .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder
                                    .create()
                                    .setSslContext(SSLContexts
                                            .custom()
                                            .loadTrustMaterial(null, (chain, authType) -> true)
                                            .build())
                                    .setHostnameVerifier((hostname, session) -> true)
                                    .build())
                            .build())
                    .evictExpiredConnections()
                    .build());
        } catch (GeneralSecurityException gse) {
            throw new RestClientException("HttpClient setup failed.", gse);
        }
    }
}