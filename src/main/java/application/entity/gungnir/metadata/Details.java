package application.entity.gungnir.metadata;

import lombok.Data;

import java.util.LinkedList;

public final class Details extends LinkedList<Details.Detail> {

    @Data
    public static final class Detail {
        private String rule;
        private String endpoint;
        private String view_func;
        private String methods;
    }
}
