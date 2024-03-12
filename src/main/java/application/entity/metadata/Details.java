package application.entity.metadata;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public final class Details extends LinkedList<Details.Detail> {
    @Data
    public static final class Detail {
        private String rule;
        private String endpoint;
        private String view_func;
        private List<String> methods;
    }
}