package javacourse;

import javacourse.controller.ConsoleController;
import javacourse.service.TruckParcelLoaderTower;
import javacourse.util.CsvParser;
import javacourse.util.CsvReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Стартуем приложение...");
        Main.start();
    }

    private static void start() {
        ConsoleController consoleController = new ConsoleController(
                new TruckParcelLoaderTower(),
                new CsvParser(new CsvReader()));
        consoleController.listen();
    }
}
