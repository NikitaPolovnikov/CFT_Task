package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public interface TxtFileWrite {
    @SuppressWarnings("unchecked")
    default void writeFile(Path path, Iterable<? extends CharSequence> list, boolean option)
            throws IOException {
        if (option){
            Files.write(path, list, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        else Files.write(path,list);
    }
}
