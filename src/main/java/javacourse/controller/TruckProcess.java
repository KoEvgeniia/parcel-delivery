package javacourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.InputParm;
import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.service.TruckParcelUnloader;
import javacourse.util.FileDownloader;
import javacourse.util.FileReader;
import javacourse.util.TruckParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class TruckProcess implements ProcessController {
    private final TruckParser truckParser;
    private final FileDownloader fileDownloader;
    private final ObjectMapper mapper;

    public void process(InputParm inputParm) {
        String filePath = inputParm.getFilePath().toAbsolutePath().toString();
        List<Truck> trucks = truckParser.parse(new FileReader().readAll(filePath));
        if (trucks != null) {
            if (trucks.isEmpty()) {
                log.warn("There are no trucks listed in file {}", filePath);
            } else {
                log.info("The list of trucks has been received");
                try {
                    TruckParcelUnloader truckUnloader = new TruckParcelUnloader();
                    List<Parcel> parcels = truckUnloader.unloadTruck(trucks);
                    fileDownloader.unload(Collections.singletonList(parcels), inputParm.getFilePath().getParent().toString(), mapper);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public void consoleInfo() {
        System.out.println("""
                Enter the file path in the format:
                'import {file path}'
                For example: 'import C:\\truck.json'""");
    }

    public Pattern fileComandPattern() {
        return Pattern.compile("import (.+\\..+)");
    }

    public InputParm getInputParm(Matcher matcher) {
        String filePath = matcher.group(1);
        Path path = Paths.get(filePath);
        return InputParm.builder()
                .filePath(path)
                .truckCount(null)
                .loaderType(null)
                .build();
    }
}
