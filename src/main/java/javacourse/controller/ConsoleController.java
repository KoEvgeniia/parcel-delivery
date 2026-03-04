package javacourse.controller;

import javacourse.domain.Parcel;
import javacourse.service.TruckParcelLoaderTower;
import javacourse.util.CsvParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final TruckParcelLoaderTower truckParcelLoaderTower;
    private final CsvParser csvParser;
    private final Pattern IMPORT_COMMAND_PATTERN = Pattern.compile("import (.+\\..+)");

    public void listen() {
        var scanner = new Scanner(System.in);
        log.info("Укажите путь к файлу в формате 'import {Полный путь и имя файла}'");
        while(scanner.hasNextLine()){
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.exit(0);
            }

            Matcher matcher = IMPORT_COMMAND_PATTERN.matcher(command);
            if (matcher.matches()) {
                String filePath = matcher.group(1);
                List<Parcel> parcels = csvParser.parse(filePath);
                if (parcels != null) {
                    if (parcels.isEmpty()) {
                        log.warn("В файле {} не передана ни одна посылка согласно шаблону", filePath);
                    } else {
                        log.info("Получен список посылок");
                        truckParcelLoaderTower.showTrucks(truckParcelLoaderTower.loadTruck(parcels));
                    }
                }
            }
        }
    }
}
