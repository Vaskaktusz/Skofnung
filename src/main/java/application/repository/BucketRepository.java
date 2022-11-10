package application.repository;

import application.entity.gungnir.metadata.Files;
import application.entity.skofnung.database.Bucket;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BucketRepository {
    @Autowired
    private RestTemplate restTemplate;

    public void delete(Bucket bucket) {
        restTemplate.exchange(bucket, Void.class, HttpMethod.DELETE, contextPath(bucket.getFolder(), bucket.getFile()));
    }

    public Files findByBucket(Bucket bucket) {
        Files files = new Files();
        if (StringUtils.hasText(bucket.getFile())) {
            Files.File file = new Files.File();
            file.set_embedded(restTemplate.exchange(bucket, String.class, HttpMethod.GET, contextPath(bucket.getFolder(), bucket.getFile())));
            files.put(bucket.getFile(), file);
        } else {
            files = restTemplate.exchange(bucket, Files.class, HttpMethod.GET, contextPath(bucket.getFolder()));
        }
        return files;
    }

    public void save(Bucket bucket) {
        // TODO: The actual file is not yet passed from the request to the call.
        restTemplate.exchange(bucket, Void.class, HttpMethod.PUT, contextPath(bucket.getFolder()));
    }

    private String contextPath(String... args) {
        return "/api/bucket/".concat(String.join("/", args));
    }
}