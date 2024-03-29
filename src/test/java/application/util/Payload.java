package application.util;

import application.entity.database.Address;
import application.entity.database.Bucket;
import application.entity.database.Source;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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
    SYSTEM(
            HttpMethod.POST,
            "/system"
    );

    @Setter
    private static Configuration configuration;
    private final HttpMethod method;
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

    private static <T extends Address> void prepareAddress(T address) {
        address.setLocation(configuration.getLocation());
        address.setUsername(configuration.getUsername());
        address.setPassword(configuration.getPassword());
    }

    public MockHttpServletRequestBuilder getRequest(Address content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return MockMvcRequestBuilders
                .request(method, path)
                .content(mapper.writeValueAsString(content))
                .contentType(MediaType.APPLICATION_JSON);
    }
}