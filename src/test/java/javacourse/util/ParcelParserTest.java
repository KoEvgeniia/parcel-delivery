package javacourse.util;

import javacourse.domain.Parcel;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class ParcelParserTest {
    @Test
    public void parseOneParcel() {
        ParcelParser parcelParser = new ParcelParser();
        String content = """
                777
                7777""";
        assertThat(parcelParser.parse(content)).containsExactly(
                Parcel.builder().form(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7", null}}).width(4).height(2).build()
        );
    }

    @Test
    public void parseManyParcels() {
        ParcelParser parcelParser = new ParcelParser();
        String content = """
                777
                7777
                999
                999
                999
                1""";
        assertThat(parcelParser.parse(content)).containsExactlyInAnyOrder(
                Parcel.builder().form(new String[][]{{"7", "7", "7", "7"}, {"7", "7", "7", null}}).width(4).height(2).build(),
                Parcel.builder().form(new String[][]{{"9", "9", "9"}, {"9", "9", "9"}, {"9", "9", "9"}}).width(3).height(3).build(),
                Parcel.builder().form(new String[][]{{"1"}}).width(1).height(1).build()
        );
    }

    @Test
    public void parseEmptyContent() {
        ParcelParser parcelParser = new ParcelParser();
        assertThat(parcelParser.parse("")).isEqualTo(null);
    }

    @Test
    public void parseIncorrectContent() {
        ParcelParser parcelParser = new ParcelParser();
        String content = """
                000""";
        assertThat(parcelParser.parse(content)).isEqualTo(Collections.emptyList());
    }
}