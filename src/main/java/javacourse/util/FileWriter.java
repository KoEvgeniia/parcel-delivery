package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Path;
import java.util.List;

public interface FileWriter<T> {
    void unload(List<T> objects, Path pathToFolder, ObjectMapper mapper);
}
