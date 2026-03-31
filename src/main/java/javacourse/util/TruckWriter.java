package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class TruckWriter implements FileWriter<Truck> {
    public void unload(List<Truck> objects, Path pathToFolder, ObjectMapper mapper) {
        ObjectMapper copyMapper = mapper.copy();
        copyMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String downloadPath = pathToFolder.resolve("truck.json").toString();
        try {
            copyMapper.writeValue(new File(downloadPath), objects);
            log.info("A file with a list of trucks has been generated {}", downloadPath);
        } catch (Exception e) {
            log.error("Error downloading file:{}", e.getMessage());
        }
    }
}
