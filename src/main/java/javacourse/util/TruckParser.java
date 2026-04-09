package javacourse.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Utility for parsing a line with trucks
 */
@Slf4j
@RequiredArgsConstructor
public class TruckParser {
    private final ObjectMapper objectMapper;

    /**
     * Parses a line with trucks
     * @param content line with content
     * @return list of trucks
     */
    public List<Truck> parse(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        List<Truck> trucks = null;
        try {
            trucks = objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
           log.error("Error while parsing file: {}", e.getMessage());
        }

        return trucks;
    }
}
