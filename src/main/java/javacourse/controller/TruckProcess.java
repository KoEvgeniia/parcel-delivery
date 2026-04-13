package javacourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.InputParm;
import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.exception.ProcessCommandException;
import javacourse.service.TruckParcelUnloader;
import javacourse.util.FileWriter;
import javacourse.util.FileReader;
import javacourse.util.TruckParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes the unloading of parcels from trucks
 */
@Slf4j
@RequiredArgsConstructor
public class TruckProcess implements ProcessController {
    private final TruckParser truckParser;
    private final FileWriter<Parcel> fileWriter;
    private final ObjectMapper mapper;
    private final Pattern UNLOAD_COMMAND_PATTERN = Pattern.compile("unload -infile (\".*\") -outfile (\".*\") ?(-withcount)?");

    /**
     * Processes the unloading of parcels from trucks
     *
     * @param inputParm An object with parameters for processing: file path, list of text parcels, load type, output type, etc
     * @return success message
     */
    public String process(InputParm inputParm) {
        try {
            String filePath = inputParm.getFilePath().toAbsolutePath().toString();
            List<Truck> trucks = truckParser.parse(new FileReader().readAll(filePath));
            if (trucks != null) {
                if (trucks.isEmpty()) {
                    throw new ProcessCommandException("There are no trucks listed in file " + filePath);
                } else {
                    log.info("The list of trucks has been received");

                    TruckParcelUnloader truckUnloader = new TruckParcelUnloader();
                    List<Parcel> parcels = truckUnloader.unloadTruck(trucks);
                    return fileWriter.unload(parcels, inputParm, mapper);
                }
            }
            return "";
        } catch (Exception e) {
            throw new ProcessCommandException(e.getMessage());
        }
    }

    /**
     * Gets an object with input parameters from a command
     *
     * @param matcher Matcher with input command text for parsing incoming data
     * @return An object with incoming parameters
     */
    public InputParm getInputParm(Matcher matcher) {
        String filePath = matcher.group(1).replace("\"", "").trim();
        Path path = Paths.get(filePath);

        String fileName = matcher.group(2).replace("\"", "").trim();
        boolean withCount = matcher.group(3) != null && !matcher.group(3).isEmpty();

        return InputParm.builder()
                .filePath(path)
                .truckCount(null)
                .loaderType(null)
                .fileName(fileName)
                .withCount(withCount)
                .build();
    }

    /**
     * Gets a matcher from an incoming command
     *
     * @param command Incoming command text
     * @return Matcher for easy parsing of incoming commands
     */
    public Matcher getMatcher(String command) {
        return UNLOAD_COMMAND_PATTERN.matcher(command);
    }
}
