package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.InputParm;

import java.util.List;

/**
 * File writing utility
 * @param <T>
 */
public interface FileWriter<T> {
    /**
     * Writes file
     * @param objects list of parcels ot trucks
     * @param inputParm object with incoming parameters
     * @param mapper class for working with JSON files
     */
    void unload(List<T> objects, InputParm inputParm, ObjectMapper mapper);
}
