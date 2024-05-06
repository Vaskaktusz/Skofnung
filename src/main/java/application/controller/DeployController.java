package application.controller;

import application.entity.database.Source;
import application.repository.DeployRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public final class DeployController {
    private final DeployRepository deployRepository;

    @PutMapping("/deploy")
    public String deploy(@RequestBody @Valid Source source) {
        return deployRepository.deploy(source);
    }
}