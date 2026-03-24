package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public interface FileDownloader {
    void unload(List<Object> objects, String path, ObjectMapper mapper);
}
