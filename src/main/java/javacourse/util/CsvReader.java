package javacourse.util;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public class CsvReader {

    public String readAll(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (Files.exists(path)) {
                log.info("Чтение файла {}", filePath);
                return Files.readString(path);
            } else {
                log.warn("Файл {} не найден", filePath);
                return "";
            }
        } catch (Exception e) {
            log.error("Ошибка обработки файла {}",e.getMessage());
            return "";
        }
    }
}
