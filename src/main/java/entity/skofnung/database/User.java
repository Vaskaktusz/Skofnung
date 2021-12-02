package entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Group {
    private String username;
    private String password;
}
