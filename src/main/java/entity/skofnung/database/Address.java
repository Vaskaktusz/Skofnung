package entity.skofnung.database;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Address extends User {
    private String address;
    private String alias;
}
