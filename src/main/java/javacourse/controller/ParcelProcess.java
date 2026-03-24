package javacourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.InputParm;
import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import javacourse.factory.LoaderFactory;
import javacourse.domain.LoaderType;
import javacourse.service.TruckParcelLoader;
import javacourse.util.FileDownloader;
import javacourse.util.FileReader;
import javacourse.util.ParcelParser;
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
public class ParcelProcess implements ProcessController {
    private final ParcelParser parcelParser;
    private final LoaderFactory loaderFactory;
    private final FileDownloader fileDownloader;
    private final ObjectMapper mapper;

    public void process(InputParm inputParm) {
        String filePath = inputParm.getFilePath().toAbsolutePath().toString();
        List<Parcel> parcels = parcelParser.parse(new FileReader().readAll(filePath));
        if (parcels != null) {
            if (parcels.isEmpty()) {
                log.warn("File {} did not contain any parcels according to the template", filePath);
            } else {
                log.info("The list of parcels has been received");
                try {
                    TruckParcelLoader truckParcelLoader = loaderFactory.createTruckParcelLoader(inputParm.getLoaderType());
                    List<Truck> trucks = truckParcelLoader.loadTruck(parcels, inputParm.getTruckCount());
                    truckParcelLoader.showTrucks(trucks);
                    fileDownloader.unload(Collections.singletonList(trucks), inputParm.getFilePath().getParent().toString(), mapper);
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
    }

    public void consoleInfo() {
        System.out.println("""
                Enter the file path, the count of trucks and the loader type in the format:
                'import {file path} {count of trucks} {loader type: ONE_TO_ONE/TOWER}'
                For example: 'import C:\\parcel.txt 5 TOWER'""");
    }

    public Pattern fileComandPattern() {
        return Pattern.compile("import (.+\\..+) (\\d+) (ONE_BY_ONE|TOWER)");
    }

    public InputParm getInputParm(Matcher matcher) {
        String filePath = matcher.group(1);
        Path path = Paths.get(filePath);
        Long truckCount = Long.valueOf(matcher.group(2));
        LoaderType loaderType = LoaderType.valueOf(matcher.group(3));
        return InputParm.builder()
                .filePath(path)
                .truckCount(truckCount)
                .loaderType(loaderType)
                .build();
    }
}
