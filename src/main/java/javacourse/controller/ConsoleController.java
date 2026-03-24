package javacourse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.factory.ProcessFactory;
import javacourse.domain.InputDataType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@RequiredArgsConstructor
public class ConsoleController {
    private final ObjectMapper mapper;
    private final Pattern INPUT_DATA_TYPE_COMMAND_PATTERN = Pattern.compile("(PARCEL|TRUCK)");

    public void listen() {
        var scanner = new Scanner(System.in);
        System.out.println("Enter the input data type to import: PARCEL or TRUCK");
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("exit")) {
                System.exit(0);
            }
            Matcher matcher = INPUT_DATA_TYPE_COMMAND_PATTERN.matcher(command);
            if (matcher.matches()) {
                InputDataType inputDataType = InputDataType.valueOf(matcher.group(1));
                ProcessController processController = new ProcessFactory().createProcessController(inputDataType, mapper);
                processController.consoleInfo();

                command = scanner.nextLine();
                matcher = processController.fileComandPattern().matcher(command);
                if (matcher.matches()) {
                    processController.process(processController.getInputParm(matcher));
                }
            }
        }
    }
}
