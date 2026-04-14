package javacourse.controller;

import javacourse.domain.InputParm;

import java.util.regex.Matcher;

/**
 * Provides methods for handling parcels and trucks
 */
public interface ProcessController {
    /**
     * Processes parcels and trucks according to the transmitted parameters
     *
     * @param inputParm An object with parameters for processing: file path, list of text parcels, load type, output type, etc
     * @return success message
     */
    String process(InputParm inputParm);

    /**
     * Gets an object with input parameters from a command
     *
     * @param matcher Matcher with input command text for parsing incoming data
     * @return An object with incoming parameters
     */
    InputParm getInputParm(Matcher matcher);

    /**
     * Gets a matcher from an incoming command
     *
     * @param command Incoming command text
     * @return Matcher for easy parsing of incoming commands
     */
    Matcher getMatcher(String command);
}
