package javacourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.*;
import javacourse.exception.LoadCommandIncorrectException;
import javacourse.exception.ProcessCommandException;
import javacourse.factory.LoaderFactory;
import javacourse.service.TruckParcelLoader;
import javacourse.util.FileWriter;
import javacourse.util.FileReader;
import javacourse.util.ParcelParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes the loading of parcels into trucks
 */
@Slf4j
@RequiredArgsConstructor
public class ParcelProcess implements ProcessController {
    private final ParcelParser parcelParser;
    private final LoaderFactory loaderFactory;
    private final FileWriter<Truck> fileWriter;
    private final ObjectMapper mapper;
    private static final String LOAD_TYPE_TEXT = "text";
    private static final String LOAD_OUT_TYPE_TEXT = "text";
    private static final String LOAD_OUT_TYPE_JSON = "json-file";
    private static final Pattern LOAD_COMMAND_PATTERN = Pattern.compile("load -parcels -(text|file) (\".*\") -trucks (\\d+) -type (TOWER|ONE_BY_ONE) -out (text|json-file) ?(-out-filename)? ?(\".*\\.json\")?");

    /**
     * Processes the loading of parcels into trucks
     *
     * @param inputParm An object with parameters for processing: file path, list of text parcels, load type, output type, etc
     * @return success message
     */
    public String process(InputParm inputParm) {
        String content = "";
        String successMessage = "";
        try {
            if (inputParm.getFilePath() != null) {
                String filePath = inputParm.getFilePath().toAbsolutePath().toString();
                content = new FileReader().readAll(filePath);
            } else if (inputParm.getParcelsText() != null && !inputParm.getParcelsText().isEmpty()) {
                content = inputParm.getParcelsText();
            }

            List<Parcel> parcels = parcelParser.parse(content);
            if (parcels != null) {
                if (parcels.isEmpty()) {
                    throw new ProcessCommandException("Empty parcel list");
                } else {
                    log.info("The list of parcels has been received");
                    TruckParcelLoader truckParcelLoader = loaderFactory.createTruckParcelLoader(inputParm.getLoaderType());
                    List<Truck> trucks = truckParcelLoader.loadTruck(parcels, inputParm.getTruckCount());
                    switch (inputParm.getOutType()) {
                        case LoadParcelOutType.TEXT:
                            successMessage = truckParcelLoader.showTrucks(trucks);
                            break;
                        case LoadParcelOutType.JSON_FILE:
                            successMessage = fileWriter.unload(trucks, inputParm, mapper);
                            break;
                    }
                }
            }
            return successMessage;
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
        String filePath;
        String text = "";
        String fileName = "";
        Path path = null;
        LoadParcelOutType outType = LoadParcelOutType.NONE;
        String loadType = matcher.group(1);
        String LOAD_TYPE_FILE = "file";
        if (loadType.equals(LOAD_TYPE_FILE)) {
            filePath = matcher.group(2).replace("\"", "").trim();
            path = Paths.get(filePath);
        } else if (loadType.equals(LOAD_TYPE_TEXT)) {
            text = matcher.group(2).replace("\"", "").trim();
        }

        Long truckCount = Long.valueOf(matcher.group(3));
        LoaderType loaderType = LoaderType.valueOf(matcher.group(4));

        String outTypeCommand = matcher.group(5);
        if (outTypeCommand.equals(LOAD_OUT_TYPE_TEXT)) {
            outType = LoadParcelOutType.TEXT;
        } else if (outTypeCommand.equals(LOAD_OUT_TYPE_JSON)) {
            outType = LoadParcelOutType.JSON_FILE;
        }

        if (outType == LoadParcelOutType.JSON_FILE) {
            if (matcher.group(6) == null || matcher.group(7).isEmpty()) {
                throw new LoadCommandIncorrectException("To output the result to a json file, you must pass the -out-filename tag and the file name");
            }

            if (loadType.equals(LOAD_TYPE_TEXT)) {
                throw new LoadCommandIncorrectException("When receiving parcels in text, -out cannot be json-file");
            }

            fileName = matcher.group(7).replace("\"", "").trim();
        }


        return InputParm.builder()
                .filePath(path)
                .truckCount(truckCount)
                .loaderType(loaderType)
                .parcelsText(text)
                .outType(outType)
                .fileName(fileName)
                .build();
    }

    /**
     * Gets a matcher from an incoming command
     *
     * @param command Incoming command text
     * @return Matcher for easy parsing of incoming commands
     */
    public Matcher getMatcher(String command) {
        return LOAD_COMMAND_PATTERN.matcher(command);
    }
}
