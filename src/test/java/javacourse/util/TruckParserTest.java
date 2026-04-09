package javacourse.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import javacourse.domain.Parcel;
import javacourse.domain.Truck;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TruckParserTest {
    private TruckParser truckParser;

    @BeforeEach
    void setUp() {
        truckParser = new TruckParser(new ObjectMapper());
    }

    @Test
    public void parseOneTruck() {
        String content = """
                [ {
                  "truckSpace" : [[ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [null, null, null, null, null, null], [null, null, null, null, null, null], [null, null, null, null, null, null], [null, null, null, null, null, null]],
                  "width" : 6,
                  "height" : 6,
                  "parcels" : [ {
                      "height" : 2,
                      "width" : 4,
                      "form" : [ [ "7", "7", "7", "7" ], [ "7", "7", "7" ] ],
                      "name" : "Parcel type 7"
                    } ]
                } ]""";
        assertThat(truckParser.parse(content)).containsExactly(
                Truck.builder().truckSpace(new Character[][]{{'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}, {null, null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).height(2).width(4).name("Parcel type 7").build()))
                        .build()
        );
    }

    @Test
    public void parseManyTrucks() {
        String content = """
                [ {
                  "truckSpace" : [ [ "5", "5", "5", "5", "5", null ], [ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ] ],
                  "width" : 6,
                  "height" : 6,
                  "parcels" : [ {
                      "height" : 1,
                      "width" : 5,
                      "form" : [ [ "5", "5", "5", "5", "5" ] ],
                      "name" : "Parcel type 5"
                    }, {
                      "height" : 2,
                      "width" : 4,
                      "form" : [ [ "7", "7", "7", "7" ], [ "7", "7", "7" ] ],
                      "name" : "Parcel type 7"
                    }, {
                      "height" : 3,
                      "width" : 3,
                      "form" : [ [ "9", "9", "9" ], [ "9", "9", "9" ], [ "9", "9", "9" ] ],
                      "name" : "Parcel type 9"
                    } ]
                }, {
                  "truckSpace" : [ [ "6", "6", "6", null, null, null ], [ "6", "6", "6", null, null, null ], [ "3", "3", "3", null, null, null ], [ "1", null, null, null, null, null ], [ "1", null, null, null, null, null ], [ null, null, null, null, null, null ] ],
                  "width" : 6,
                  "height" : 6,
                  "parcels" : [ {
                      "height" : 2,
                      "width" : 3,
                      "form" : [ [ "6", "6", "6" ], [ "6", "6", "6" ] ],
                      "name" : "Parcel type 6"
                    }, {
                      "height" : 1,
                      "width" : 3,
                      "form" : [ [ "3", "3", "3" ] ],
                      "name" : "Parcel type 3"
                    }, {
                      "height" : 1,
                      "width" : 1,
                      "form" : [ [ "1" ] ],
                      "name" : "Parcel type 1"
                    }, {
                      "height" : 1,
                      "width" : 1,
                      "form" : [ [ "1" ] ],
                      "name" : "Parcel type 1"
                    } ]
                } ]""";
        assertThat(truckParser.parse(content)).containsExactlyInAnyOrder(
                Truck.builder().truckSpace(new Character[][]{{'5', '5', '5', '5', '5', null}, {'7', '7', '7', '7', null, null}, {'7', '7', '7', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}, {'9', '9', '9', null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'5', '5', '5', '5', '5'}}).width(5).height(1).name("Parcel type 5").build(),
                                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).name("Parcel type 7").build(),
                                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).name("Parcel type 9").build()))
                        .build(),
                Truck.builder().truckSpace(new Character[][]{{'6', '6', '6', null, null, null}, {'6', '6', '6', null, null, null}, {'3', '3', '3', null, null, null}, {'1', null, null, null, null, null}, {'1', null, null, null, null, null}, {null, null, null, null, null, null}})
                        .width(6)
                        .height(6)
                        .parcels(List.of(Parcel.builder().form(new Character[][]{{'6', '6', '6'}, {'6', '6', '6'}}).height(2).width(3).name("Parcel type 6").build(),
                                Parcel.builder().form(new Character[][]{{'3', '3', '3'}}).width(3).height(1).name("Parcel type 3").build(),
                                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).name("Parcel type 1").build(),
                                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).name("Parcel type 1").build()))
                        .build()
        );
    }

    @Test
    public void parseEmptyContent() {
        assertThat(truckParser.parse("")).isEqualTo(null);
    }

    @Test
    public void parseIncorrectContent() {
        String content = """
                [ {
                  "truckSpaceTest" : [ [ "5", "5", "5", "5", "5", null ], [ "7", "7", "7", "7", null, null ], [ "7", "7", "7", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ], [ "9", "9", "9", null, null, null ] ],
                  "widthTest" : 6,
                  "heightTest" : 6,
                  "parcels" : [ {
                      "height" : 2,
                      "width" : 3,
                      "form" : [ [ "6", "6", "6" ], [ "6", "6", "6" ] ],
                      "name" : "Parcel type 6"
                    }, {
                      "height" : 1,
                      "width" : 3,
                      "form" : [ [ "3", "3", "3" ] ],
                      "name" : "Parcel type 3"
                    }, {
                      "height" : 1,
                      "width" : 1,
                      "form" : [ [ "1" ] ],
                      "name" : "Parcel type 1"
                    }]
                }, {
                  "truckSpaceTest" : [ [ "6", "6", "6", null, null, null ], [ "6", "6", "6", null, null, null ], [ "3", "3", "3", null, null, null ], [ "1", null, null, null, null, null ], [ "1", null, null, null, null, null ], [ null, null, null, null, null, null ] ],
                  "widthTest" : 6,
                  "heightTest" : 6,
                  "parcels" : [ {
                      "height" : 2,
                      "width" : 3,
                      "form" : [ [ "6", "6", "6" ], [ "6", "6", "6" ] ],
                      "name" : "Parcel type 6"
                    }, {
                      "height" : 1,
                      "width" : 3,
                      "form" : [ [ "3", "3", "3" ] ],
                      "name" : "Parcel type 3"
                    }, {
                      "height" : 1,
                      "width" : 1,
                      "form" : [ [ "1" ] ],
                      "name" : "Parcel type 1"
                    }, {
                      "height" : 1,
                      "width" : 1,
                      "form" : [ [ "1" ] ],
                      "name" : "Parcel type 1"
                    } ]
                } ]""";
        assertThat(truckParser.parse(content)).isEqualTo(null);
    }
}