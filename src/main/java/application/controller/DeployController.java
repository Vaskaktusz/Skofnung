package application.controller;

import application.entity.skofnung.database.Source;
import application.repository.DeployRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class DeployController {
    @Autowired
    private DeployRepository deployRepository;

    @PutMapping("/deploys/save")
    public String save(@RequestBody @Valid Source source) {
        return deployRepository.save(source);
    }
}