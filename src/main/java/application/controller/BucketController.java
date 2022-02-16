package application.controller;

import application.entity.gungnir.metadata.Files;
import application.entity.skofnung.database.Bucket;
import application.repository.BucketRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public final record BucketController(BucketRepository bucketRepository) {
    @PostMapping("/buckets/search/findByBucket")
    public Files findByBucket(@RequestBody @Valid Bucket bucket) {
        return bucketRepository.findByBucket(bucket);
    }

    @PutMapping("/buckets/save")
    public void save(@RequestBody @Valid Bucket bucket) {
        bucketRepository.save(bucket);
    }
}
