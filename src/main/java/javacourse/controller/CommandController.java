package javacourse.controller;

import javacourse.domain.InputDataType;
import javacourse.domain.Parcel;
import javacourse.dto.CommandResultDto;
import javacourse.service.ParcelHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
     *
     * @param command entered command
     * @return dto with command result info
     */
    public CommandResultDto command(String command) {
        Matcher matcher = INPUT_DATA_TYPE_COMMAND_PATTERN.matcher(command);
        CommandResultDto result = CommandResultDto.builder().build();
        if (matcher.matches()) {
            InputDataType inputDataType = InputDataType.valueOf(matcher.group(1).toUpperCase());
            try {
                switch (inputDataType) {
                    case LOAD -> {
                        Matcher matcherLoad = parcelProcess.getMatcher(command);
                        if (matcherLoad != null && matcherLoad.matches()) {
                            result.setMessage(parcelProcess.process(parcelProcess.getInputParm(matcherLoad)));
                        } else {
                            result.setDebug(invalidCommandText());
                        }
                    }
                    case UNLOAD -> {
                        Matcher matcherUnload = truckProcess.getMatcher(command);
                        if (matcherUnload != null && matcherUnload.matches()) {
                            result.setMessage(truckProcess.process(truckProcess.getInputParm(matcherUnload)));
                        } else {
                            result.setDebug(invalidCommandText());
                        }
                    }
                    case FINDALL -> {
                        StringBuilder sb = new StringBuilder();
                        parcelHandler.findAll().forEach(parcel -> sb.append(parcel.toString()));
                        result.setMessage(sb.toString());
                    }
                    case FIND -> {
                        String parcelName = command.replace(matcher.group(1), "").trim();
                        parcelName = parcelName.replace("\"", "").trim();

                        result.setMessage(parcelHandler.findByName(parcelName).toString());
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
                            result.setMessage(parcelHandler.create(parcel).toString());
                        } else {
                            result.setDebug(invalidCommandText());
                        }
                    }

                    case DELETE -> {
                        String parcelName = command.replace(matcher.group(1), "").trim();
                        parcelName = parcelName.replace("\"", "").trim();

                        parcelHandler.deleteByName(parcelName);
                        result.setMessage(parcelName + " deleted");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + inputDataType);
                }
            } catch (Exception e) {
                result.setDebug(e.getMessage());
                log.error(e.getMessage());
            }
        }
        return result;
    }

    private String invalidCommandText() {
        return "Invalid command";
    }
}
