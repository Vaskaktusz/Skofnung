package application.entity.skofnung.database;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Source extends Bucket {
    @NotBlank
    private String script;
}