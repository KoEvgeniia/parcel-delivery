package javacourse.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * Processes entered commands from the console
 */
@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final CommandController commandController;

    /**
     * Listens and processes for commands from the console
     */
    public void listen() {
        var scanner = new Scanner(System.in);
        log.info("Enter command");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.exit(0);
            }

            commandController.command(command);
            log.info("Enter command");
        }
    }
}
