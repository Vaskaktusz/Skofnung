package application.controller;

import application.repository.BucketRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final record BucketController(BucketRepository bucketRepository) {
}
