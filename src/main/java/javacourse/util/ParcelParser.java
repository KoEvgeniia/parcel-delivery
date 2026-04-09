package javacourse.util;

import javacourse.domain.Parcel;
import javacourse.service.ParcelHandler;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility for parsing a line with parcels
 */
@RequiredArgsConstructor
public class ParcelParser {
    private final ParcelHandler parcelHandler;

    /**
     * Parses a line with parcels
     * @param content line with content
     * @return list of parcels
     */
    public List<Parcel> parse(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }

        String[] parcelNames = content.replace("\"", "").split("\\\\n|\\r\\n");
        return Arrays.stream(parcelNames).map(parcelHandler::findByName).collect(Collectors.toList());
    }
}
