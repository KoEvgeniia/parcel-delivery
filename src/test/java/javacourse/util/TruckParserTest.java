package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.Truck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParserTest {
    @Test
    public void parseOneTruck() {
        TruckParser truckParser = new TruckParser(new ObjectMapper());
        String content = """
                [ {
                  "truckSpace" : [[ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [null, null, null, null, null, null], [null, null, null, null, null, null], [null, null, null, null, null, null], [null, null, null, null, null, null]],
                  "width" : 6,
                  "height" : 6
                } ]""";
        assertThat(truckParser.parse(content)).containsExactly(
                Truck.builder().truckSpace(new String[][]{{"7", "7", "7", "7", null, null}, {"7", "7", "7", null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}}).width(6).height(6).build()
        );
    }

    @Test
    public void parseManyTrucks() {
        TruckParser truckParser = new TruckParser(new ObjectMapper());
        String content = """
                [ {
                  "truckSpace" : [ [ "5", "5", "5", "5", "5", null ], [ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ] ],
                  "width" : 6,
                  "height" : 6
                }, {
                  "truckSpace" : [ [ "6", "6", "6", null, null, null ], [ "6", "6", "6", null, null, null ], [ "3", "3", "3", null, null, null ], [ "1", null, null, null, null, null ], [ "1", null, null, null, null, null ], [ null, null, null, null, null, null ] ],
                  "width" : 6,
                  "height" : 6
                } ]\s""";
        assertThat(truckParser.parse(content)).containsExactlyInAnyOrder(
                Truck.builder().truckSpace(new String[][]{{"5", "5", "5", "5", "5", null}, {"7", "7", "7", "7", null, null}, {"7", "7", "7", null, null, null}, {"9", "9", "9", null, null, null}, {"9", "9", "9", null, null, null}, {"9", "9", "9", null, null, null}}).width(6).height(6).build(),
                Truck.builder().truckSpace(new String[][]{{"6", "6", "6", null, null, null}, {"6", "6", "6", null, null, null}, {"3", "3", "3", null, null, null}, {"1", null, null, null, null, null}, {"1", null, null, null, null, null}, {null, null, null, null, null, null}}).width(6).height(6).build()
        );
    }

    @Test
    public void parseEmptyContent() {
        TruckParser truckParser = new TruckParser(new ObjectMapper());
        assertThat(truckParser.parse("")).isEqualTo(null);
    }

    @Test
    public void parseIncorrectContent() {
        TruckParser truckParser = new TruckParser(new ObjectMapper());
        String content = """
                [ {
                  "truckSpaceTest" : [ [ "5", "5", "5", "5", "5", null ], [ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ] ],
                  "widthTest" : 6,
                  "heightTest" : 6
                }, {
                  "truckSpaceTest" : [ [ "6", "6", "6", null, null, null ], [ "6", "6", "6", null, null, null ], [ "3", "3", "3", null, null, null ], [ "1", null, null, null, null, null ], [ "1", null, null, null, null, null ], [ null, null, null, null, null, null ] ],
                  "widthTest" : 6,
                  "heightTest" : 6
                } ]\s""";
        assertThat(truckParser.parse(content)).isEqualTo(null);
    }
}