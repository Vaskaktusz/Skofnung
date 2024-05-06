package application.controller;

import application.entity.database.Bucket;
import application.entity.database.Source;
import application.entity.metadata.Files;
import application.repository.BucketRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class BucketController {
    private final BucketRepository bucketRepository;

    @DeleteMapping("/buckets")
    public void delete(@RequestBody @Valid Bucket bucket) {
        bucketRepository.delete(bucket);
    }

    @PutMapping("/buckets")
    public void save(@RequestBody @Valid Source bucket) {
        bucketRepository.save(bucket);
    }

    @PostMapping("/buckets/search")
    public Files search(@RequestBody @Valid Bucket bucket) {
        return bucketRepository.search(bucket);
    }
}