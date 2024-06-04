package application.controller;

import application.entity.database.Bucket;
import application.entity.database.Program;
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
final class BucketController {
    private final BucketRepository bucketRepository;

    @DeleteMapping("/buckets")
    void delete(@RequestBody @Valid Bucket bucket) {
        bucketRepository.delete(bucket);
    }

    @PutMapping("/buckets")
    void save(@RequestBody @Valid Program bucket) {
        bucketRepository.save(bucket);
    }

    @PostMapping("/buckets/search")
    Files search(@RequestBody @Valid Bucket bucket) {
        return bucketRepository.search(bucket);
    }
}