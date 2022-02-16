package application.entity.gungnir.metadata;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

public final class Files extends LinkedList<Files.File> {

    @Data
    public static final class File {
        private final String _embedded;
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
