package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.InputParm;
import javacourse.domain.Parcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utility for writing a file with parcels
 */
@Slf4j
@RequiredArgsConstructor
public class ParcelWriter implements FileWriter<Parcel> {
    /**
     * Writes file with parcels
     * @param objects list of parcels ot trucks
     * @param inputParm object with incoming parameters
     * @param mapper class for working with JSON files
     */
    public void unload(List<Parcel> objects, InputParm inputParm, ObjectMapper mapper) {
        String downloadPath = inputParm.getFilePath().getParent().resolve(inputParm.getFileName()).toString();
        StringBuilder fileContent = new StringBuilder();
        try {
            fileContent.append(toStringParcels(objects, inputParm.isWithCount()));
            Files.writeString(Paths.get(downloadPath), fileContent);
            log.info("A file with a list of parcels has been generated {}", downloadPath);
        } catch (Exception e) {
            log.error("Error writing file:{}", e.getMessage());
        }
    }

    private String toStringParcels(List<Parcel> parcels, boolean withCount) {
        StringBuilder str = new StringBuilder();

        Map<String, Long> parcelsByTruck = parcels.stream().collect(Collectors.groupingBy(Parcel::getName, Collectors.counting()));
        for (Map.Entry<String, Long> entry : parcelsByTruck.entrySet()) {
            str.append("\"").append(entry.getKey()).append("\"");
            if (withCount) {
                str.append(";").append(entry.getValue()).append(System.lineSeparator());
            } else {
                str.append(System.lineSeparator());
            }
        }

        return str.toString();
    }
}
