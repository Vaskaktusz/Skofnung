package entity;

import lombok.Data;

@Data
public final class Detail {
    private String rule;
    private String endpoint;
    private String view_func;
    private String methods;
}
