package application.entity.skofnung.database;

import application.converter.DatabaseEncryptor;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Address extends User {
    @Convert(converter = DatabaseEncryptor.class)
    @NotBlank
    private String location;
}