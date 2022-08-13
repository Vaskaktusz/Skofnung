package application.entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends Id {
    @NotBlank
    private String location;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
