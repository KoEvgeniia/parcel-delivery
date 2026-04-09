package javacourse.controller;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import javacourse.domain.InputDataType;
import javacourse.domain.Parcel;
import javacourse.service.ParcelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Processes entered commands
 */
@Slf4j
@RequiredArgsConstructor
public class CommandController {
    private final ParcelHandler parcelHandler;
    private final ParcelProcess parcelProcess;
    private final TruckProcess truckProcess;
    private static final Pattern INPUT_DATA_TYPE_COMMAND_PATTERN = Pattern.compile("(create|findAll|find|delete|load|unload).*");
    private static final Pattern CREATE_COMMAND_PATTERN = Pattern.compile("create -name (.*) -form (.*)");

    /**
     * Processes entered commands
     * @param command entered command
     * @return command processing log
     */
    public String command(String command) {
        Matcher matcher = INPUT_DATA_TYPE_COMMAND_PATTERN.matcher(command);
        StringBuilder sb = new StringBuilder();
        if (matcher.matches()) {
            Logger logbackLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
            ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
            listAppender.start();
            logbackLogger.addAppender(listAppender);
            InputDataType inputDataType = InputDataType.valueOf(matcher.group(1).toUpperCase());
            try {
                switch (inputDataType) {
                    case LOAD -> {
                        Matcher matcherLoad = parcelProcess.getMatcher(command);
                        if (matcherLoad != null && matcherLoad.matches()) {
                            parcelProcess.process(parcelProcess.getInputParm(matcherLoad));
                        } else {
                            invalidCommandLog();
                        }
                    }
                    case UNLOAD -> {
                        Matcher matcherUnload = truckProcess.getMatcher(command);
                        if (matcherUnload != null && matcherUnload.matches()) {
                            truckProcess.process(truckProcess.getInputParm(matcherUnload));
                        } else {
                            invalidCommandLog();
                        }
                    }
                    case FINDALL ->
                            parcelHandler.findAll().forEach(parcel -> log.info(parcel.toString()));
                    case FIND -> {
                        String parcelName = command.replace(matcher.group(1), "").trim();
                        parcelName = parcelName.replace("\"", "").trim();

                        log.info(parcelHandler.findByName(parcelName).toString());
                    }
                    case CREATE -> {
                        Matcher matcherCreate = CREATE_COMMAND_PATTERN.matcher(command);
                        if (matcherCreate.matches()) {
                            String[] form = matcherCreate.group(2).replace("\"", "").split("\\\\n");
                            Character[][] parcelForm = new Character[form.length][form[form.length - 1].length()];
                            for (int i = form.length - 1; i >= 0; i--) {
                                for (int j = 0; j < form[i].length(); j++) {
                                    parcelForm[form.length - 1 - i][j] = form[i].charAt(j);
                                }
                            }
                            Parcel parcel = Parcel.builder()
                                    .name(matcherCreate.group(1).replace("\"", "").trim())
                                    .form(parcelForm)
                                    .height(form.length)
                                    .width(parcelForm[0].length)
                                    .build();
                            log.info(parcelHandler.create(parcel).toString());
                        } else {
                            invalidCommandLog();
                        }
                    }

                    case DELETE -> {
                        String parcelName = command.replace(matcher.group(1), "").trim();
                        parcelName = parcelName.replace("\"", "").trim();

                        parcelHandler.deleteByName(parcelName);
                        log.info("{} deleted", parcelName);
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + inputDataType);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            List<ILoggingEvent> logs = listAppender.list;

            for (ILoggingEvent event : logs) {
                sb.append(event.getFormattedMessage()).append("\n");
            }

            listAppender.stop();
            logbackLogger.detachAppender(listAppender);
        }
        return sb.toString();
    }

    private void invalidCommandLog() {
        log.error("Invalid command");
    }
}
