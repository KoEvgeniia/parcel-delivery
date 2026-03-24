package javacourse.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.Truck;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TruckParser {
    private final ObjectMapper objectMapper;

    public List<Truck> parse(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        List<Truck> trucks = null;
        try {
            trucks = objectMapper.readValue(content, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            e.getMessage();
        }

        return trucks;
    }
}
