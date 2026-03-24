package javacourse;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.controller.ConsoleController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Starting...");
        Main.start();
    }

    private static void start() {
        ConsoleController consoleController = new ConsoleController(
                new ObjectMapper());
        consoleController.listen();
    }
}
