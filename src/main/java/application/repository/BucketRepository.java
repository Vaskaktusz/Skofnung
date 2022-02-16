package application.repository;

import application.entity.gungnir.metadata.Files;
import application.entity.skofnung.database.Bucket;
import application.facade.BucketFacade;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BucketRepository extends BucketFacade {
    public void delete(Bucket bucket) {
        // TODO: FIXME!
    }

    public Files findByBucket(Bucket bucket) {
        Files files = new Files();
        String contextPath = String.format("/api/bucket/%s", bucket.getFolder());
        if (StringUtils.hasText(bucket.getFile())) {
            contextPath = contextPath.concat(String.format("/%s", bucket.getFile()));
            files.add(new Files.File(bodyToMono(bucket, String.class, contextPath)));
        } else {
            files = bodyToMono(bucket, Files.class, contextPath);
        }
        return files;
    }

    public void save(Bucket bucket) {
        // TODO: FIXME!
    }
}
