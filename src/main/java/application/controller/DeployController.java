package application.controller;

import application.entity.skofnung.database.Address;
import application.repository.DeployRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DeployController {
    @Autowired
    private DeployRepository deployRepository;

    @PutMapping("/deploys/save")
    public void save(@RequestBody @Valid Address address) {
        deployRepository.save(address);
    }
}
