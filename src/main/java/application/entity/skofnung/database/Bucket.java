package application.entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public final class Bucket extends Address {
    private String file;
    private String folder;
}
