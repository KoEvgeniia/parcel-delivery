package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TruckDownloader implements FileDownloader {
    public void unload(List<Object> objects, String path, ObjectMapper mapper) {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String downloadPath = path + "\\truck.json";
        try {
            mapper.writeValue(new File(downloadPath), objects);
            log.info("A file with a list of trucks has been generated {}", downloadPath);
        } catch (Exception e) {
            log.error("Error downloading file:{}", e.getMessage());
        }
    }
}
