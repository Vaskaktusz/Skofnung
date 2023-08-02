package application.repository;

import application.entity.gungnir.metadata.Files;
import application.entity.skofnung.database.Bucket;
import application.entity.skofnung.database.Source;
import application.service.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Repository
public class BucketRepository {
    @Autowired
    private RestTemplate restTemplate;

    public void delete(Bucket bucket) {
        restTemplate.httpDelete(bucket, contextPath(bucket.getFolder(), bucket.getFile()));
    }

    public Files findByBucket(Bucket bucket) {
        Files files = new Files();
        if (StringUtils.hasText(bucket.getFile())) {
            Files.File file = new Files.File();
            file.set_embedded(restTemplate.httpGet(bucket, contextPath(bucket.getFolder(), bucket.getFile()), String.class));
            files.put(bucket.getFile(), file);
        } else {
            files = restTemplate.httpGet(bucket, contextPath(bucket.getFolder()), Files.class);
        }
        return files;
    }

    public void save(Source source) {
        restTemplate.httpPut(source, contextPath(source.getFolder(), source.getFile()));
    }

    private String contextPath(String... args) {
        return "/api/bucket/".concat(String.join("/", Arrays.stream(args).filter(StringUtils::hasText).toList()));
    }
}