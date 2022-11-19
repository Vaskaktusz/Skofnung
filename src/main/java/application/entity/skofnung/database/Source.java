package application.entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class Source extends Bucket {
    @NotBlank
    private String script;
}