package application.entity.skofnung.database;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Bucket extends Address {
    private String file;
    private String folder;
}