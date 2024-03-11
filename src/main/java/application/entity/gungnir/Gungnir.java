package application.entity.gungnir;

import application.entity.gungnir.metadata.Device;
import application.entity.gungnir.metadata.System;
import lombok.Data;

@Data
public final class Gungnir {
    private Device device;
    private System system;
}