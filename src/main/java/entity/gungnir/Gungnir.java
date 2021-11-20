package entity.gungnir;

import entity.gungnir.metadata.Details;
import entity.gungnir.metadata.Device;
import entity.gungnir.metadata.System;
import lombok.Data;

@Data
public final class Gungnir {
    private Details details;
    private Device device;
    private System system;
}
