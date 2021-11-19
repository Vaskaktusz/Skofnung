package entity.gungnir;

import entity.gungnir.metadata.Detail;
import entity.gungnir.metadata.Device;
import entity.gungnir.metadata.System;
import lombok.Data;

import java.util.List;

@Data
public final class Gungnir {
    private List<Detail> details;
    private Device device;
    private System system;
}
