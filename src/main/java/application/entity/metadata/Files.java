package application.entity.metadata;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;

public final class Files extends HashMap<String, Files.File> {
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static final class File {
        private String _embedded;
        private String mode;
        private String ino;
        private String dev;
        private String nlink;
        private String uid;
        private String gid;
        private String size;
        private String atime;
        private String mtime;
        private String ctime;
    }
}