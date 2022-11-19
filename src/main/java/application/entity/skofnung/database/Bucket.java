package application.entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Bucket extends Address {
    private String file;
    private String folder;
}