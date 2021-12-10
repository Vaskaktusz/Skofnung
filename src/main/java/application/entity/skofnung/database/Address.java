package application.entity.skofnung.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public final class Address {
    @Id
    @NotBlank
    private String location;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
