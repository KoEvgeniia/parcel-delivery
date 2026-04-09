package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javacourse.domain.InputParm;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * Utility for writing a file with trucks
 */
@Slf4j
@RequiredArgsConstructor
public class TruckWriter implements FileWriter<Truck> {
    /**
     * Writes file with trucks
     * @param objects list of parcels ot trucks
     * @param inputParm object with incoming parameters
     * @param mapper class for working with JSON files
     */
    public void unload(List<Truck> objects, InputParm inputParm, ObjectMapper mapper) {
        ObjectMapper copyMapper = mapper.copy();
        copyMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String downloadPath = inputParm.getFilePath().getParent().resolve(inputParm.getFileName()).toString();
        try {
            copyMapper.writeValue(new File(downloadPath), objects);
            log.info("A file with a list of trucks has been generated {}", downloadPath);
        } catch (Exception e) {
            log.error("Error writing file:{}", e.getMessage());
        }
    }
}
