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

        final String[] level = new String[3];
        return matcher.results().map(MatchResult::group)
                .map(parcelStr -> parcelStr.split("\\r\\n"))
                .map(parcelLevel -> {
                    switch (parcelLevel.length) {
                        case 1:
                            level[0] = parcelLevel[0];
                            level[1] = "";
                            level[2] = "";
                            break;
                        case 2:
                            level[0] = parcelLevel[1];
                            level[1] = parcelLevel[0];
                            level[2] = "";
                            break;
                        case 3:
                            level[0] = parcelLevel[2];
                            level[1] = parcelLevel[1];
                            level[2] = parcelLevel[0];
                            break;
                    }
                    return Parcel.builder()
                            .level1(level[0])
                            .level2(level[1])
                            .level3(level[2])
                            .height(parcelLevel.length)
                            .width(level[0].length())
                            .build();
                })
                .collect(Collectors.toList());
    }
}
