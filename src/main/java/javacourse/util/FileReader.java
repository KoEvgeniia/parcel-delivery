package javacourse.util;

import javacourse.exception.ReadFileException;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * File reading utility
 */
@Slf4j
public class FileReader {

    /**
     * Reads all file
     * @param filePath path to file in text
     * @return file contents in text
     */
    public String readAll(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (Files.exists(path)) {
                log.info("Reading file {}", filePath);
                return Files.readString(path);
            } else {
                throw new ReadFileException("File " + filePath + " not found");
            }
        } catch (Exception e) {
            throw new ReadFileException("Error processing file " + e.getMessage());
        }
    }
}
