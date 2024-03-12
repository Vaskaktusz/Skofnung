package application.entity.metadata;

import lombok.Data;

@Data
public final class Device {
    private String system;
    private String node;
    private String release;
    private String version;
    private String machine;
    private String processor;
}