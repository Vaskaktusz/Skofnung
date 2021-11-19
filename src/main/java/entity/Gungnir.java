package entity;

import lombok.Data;

import java.util.List;

@Data
public class Gungnir {
    private List<Detail> details;
    private Device device;
    private System system;
}
