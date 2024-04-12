package application.entity.database;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
// TODO: DeployController does not require information about file and folder.
public class Source extends Bucket {
    private String script;
}