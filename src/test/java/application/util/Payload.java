package application.util;

import application.entity.database.Address;
import application.entity.database.Bucket;
import application.entity.database.Source;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RequiredArgsConstructor
public enum Payload {
    BUCKETS_DELETE(
            HttpMethod.DELETE,
            "/buckets"
    ),
    BUCKETS_PUT(
            HttpMethod.PUT,
            "/buckets"
    ),
    BUCKETS_SEARCH(
            HttpMethod.POST,
            "/buckets/search"
    ),
    DEPLOY(
            HttpMethod.PUT,
            "/deploy"
    ),
    DEVICE(
            HttpMethod.POST,
            "/device"
    ),
    HEALTH(
            HttpMethod.POST,
            "/health"
    ),
    REGISTER(
            HttpMethod.POST,
            "/register"
    ),
    SOURCES_DELETE(
            HttpMethod.DELETE,
            "/sources"
    ),
    SOURCES_GET(
            HttpMethod.GET,
            "/sources"
    ),
    SOURCES_POST(
            HttpMethod.POST,
            "/sources"
    ),
    SYSTEM(
            HttpMethod.POST,
            "/system"
    );

    @Setter
    private static Configuration configuration;
    @Setter
    private static HttpHeaders httpHeaders;
    private final HttpMethod httpMethod;
    private final String path;

    public static Address buildAddress() {
        Address address = new Address();
        prepareAddress(address);
        return address;
    }

    public static Bucket buildBucket(String file, String folder) {
        Bucket bucket = new Bucket();
        bucket.setFile(file);
        bucket.setFolder(folder);
        prepareAddress(bucket);
        return bucket;
    }

    public static Source buildSource(String script, String file, String folder) {
        Source source = new Source();
        source.setScript(script);
        source.setFile(file);
        source.setFolder(folder);
        prepareAddress(source);
        return source;
    }

    public MockHttpServletRequestBuilder getRequest(Object content) throws JsonProcessingException {
        return getRequest(null).content(new ObjectMapper().writeValueAsString(content));
    }

    public MockHttpServletRequestBuilder getRequest(String id) {
        String url = path;
        if (id != null && !id.isBlank()) {
            url = String.join("/", url, id);
        }
        return MockMvcRequestBuilders
                .request(httpMethod, url)
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON);
    }

    private static <T extends Address> void prepareAddress(T address) {
        address.setLocation(configuration.getLocation());
        address.setUsername(configuration.getUsername());
        address.setPassword(configuration.getPassword());
    }
}