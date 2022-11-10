package application.entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Bucket extends Address {
    private String file;
    @NotBlank
    private String folder;
}