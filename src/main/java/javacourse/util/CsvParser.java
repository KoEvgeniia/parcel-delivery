package javacourse.util;

import javacourse.domain.Parcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class CsvParser {
    private final CsvReader csvReader;
    private static final String parcelsRegex = "1\\r?\\n?|22\\r?\\n?|333\\r?\\n?|4444\\r?\\n?|55555\\r?\\n?|666\\r\\n666\\r?\\n?|777\\r\\n7777\\r?\\n?|8888\\r\\n8888\\r?\\n?|999\\r\\n999\\r\\n999\\r?\\n?";

    public List<Parcel> parse(String filePath) {
        String fileContent = csvReader.readAll(filePath);

        if (fileContent.isEmpty()) {
            return null;
        }
        Pattern pattern = Pattern.compile(parcelsRegex);
        Matcher matcher = pattern.matcher(fileContent);

        return matcher.results().map(MatchResult::group)
                .map(parcelStr -> parcelStr.split("\\r\\n"))
                .map(parcelLevel -> {
                    char[][] parcelForm = new char[parcelLevel.length][parcelLevel[parcelLevel.length - 1].length()];
                    for (int i = parcelLevel.length - 1; i >= 0; i--) {
                        for (int j = 0; j < parcelLevel[i].length(); j++) {
                            parcelForm[parcelLevel.length - 1 - i][j] = parcelLevel[i].charAt(j);
                        }
                    }
                    return Parcel.builder()
                            .height(parcelLevel.length)
                            .width(parcelForm[0].length)
                            .form(parcelForm)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
