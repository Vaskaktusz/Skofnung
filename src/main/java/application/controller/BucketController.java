package application.controller;

import application.entity.gungnir.metadata.Files;
import application.entity.skofnung.database.Bucket;
import application.repository.BucketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class BucketController {
    @Autowired
    private BucketRepository bucketRepository;

    @DeleteMapping("/buckets/delete")
    public void delete(@RequestBody @Valid Bucket bucket) {
        bucketRepository.delete(bucket);
    }

    @PostMapping("/buckets/search/findByBucket")
    public Files findByBucket(@RequestBody @Valid Bucket bucket) {
        return bucketRepository.findByBucket(bucket);
    }

    @PutMapping("/buckets/save")
    public void save(@RequestBody @Valid Bucket bucket) {
        bucketRepository.save(bucket);
    }
}