package javacourse.exception;

import javacourse.domain.InputDataType;

/**
 * Exception when an invalid command is entered
 */
public class ProcessControllerIncorrectTypeException extends RuntimeException {
    /**
     * Exception when an invalid command is entered
     * @param inputDataType name of input command
     */
    public ProcessControllerIncorrectTypeException(InputDataType inputDataType) {
        super("The input data type " + inputDataType + " is incorrect.");
    }
}
