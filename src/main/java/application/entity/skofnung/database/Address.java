package application.entity.skofnung.database;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends Id {
    @NotBlank
    private String location;
    @NotBlank
    private String username;
    private String password;
}