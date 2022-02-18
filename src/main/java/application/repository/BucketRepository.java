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
        // TODO: FIXME!
    }

    public Files findByBucket(Bucket bucket) {
        Files files = new Files();
        String contextPath = String.format("/api/bucket/%s", bucket.getFolder());
        if (StringUtils.hasText(bucket.getFile())) {
            contextPath = contextPath.concat(String.format("/%s", bucket.getFile()));
            files.add(new Files.File(restTemplate.exchange(bucket, String.class, HttpMethod.GET, contextPath)));
        } else {
            files = restTemplate.exchange(bucket, Files.class, HttpMethod.GET, contextPath);
        }
        return files;
    }

    public void save(Bucket bucket) {
        // TODO: FIXME!
    }
}
