package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javacourse.domain.InputParm;
import javacourse.domain.Truck;
import javacourse.exception.FileWriterException;
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
     *
     * @param objects   list of parcels ot trucks
     * @param inputParm object with incoming parameters
     * @param mapper    class for working with JSON files
     * @return success message
     */
    public String unload(List<Truck> objects, InputParm inputParm, ObjectMapper mapper) {
        ObjectMapper copyMapper = mapper.copy();
        copyMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String downloadPath = inputParm.getFilePath().getParent().resolve(inputParm.getFileName()).toString();
        try {
            copyMapper.writeValue(new File(downloadPath), objects);
            String successMessage = "A file with a list of trucks has been generated " + downloadPath;
            log.info(successMessage);
            return successMessage;
        } catch (Exception e) {
            throw new FileWriterException("Error writing file " + e.getMessage());
        }
    }
}
