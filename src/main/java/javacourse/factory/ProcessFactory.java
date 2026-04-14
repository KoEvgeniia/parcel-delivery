package javacourse.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.controller.ParcelProcess;
import javacourse.controller.ProcessController;
import javacourse.controller.TruckProcess;
import javacourse.domain.InputDataType;
import javacourse.exception.ProcessControllerIncorrectTypeException;
import javacourse.service.ParcelHandler;
import javacourse.util.ParcelParser;
import javacourse.util.ParcelWriter;
import javacourse.util.TruckParser;
import javacourse.util.TruckWriter;

/**
 * Factory for creating a class for loading parcels or unloading trucks
 */
public class ProcessFactory {
    /**
     * Creates a class for loading parcels or unloading trucks
     * @param inputDataType input command
     * @param mapper class for working with JSON files
     * @param parcelHandler class for working with parcels in a repository
     * @return class for loading parcels or unloading trucks
     */
    public ProcessController createProcessController(InputDataType inputDataType, ObjectMapper mapper, ParcelHandler parcelHandler) {
        return switch (inputDataType) {
            case UNLOAD -> new TruckProcess(new TruckParser(mapper), new ParcelWriter(), mapper);
            case LOAD ->
                    new ParcelProcess(new ParcelParser(parcelHandler), new LoaderFactory(), new TruckWriter(), mapper);
            case CREATE, FINDALL, FIND, DELETE -> throw new ProcessControllerIncorrectTypeException(inputDataType);
        };
    }
}
