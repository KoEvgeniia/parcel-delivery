package javacourse.util;

import javacourse.domain.Parcel;
import javacourse.exception.ParcelNotFoundException;
import javacourse.repository.ParcelRepository;
import javacourse.service.ParcelHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParcelParserTest {
    private ParcelParser parcelParser;

    @BeforeEach
    void setUp() {
        parcelParser = new ParcelParser(new ParcelHandler(new ParcelRepository()));
    }

    @Test
    public void parseOneParcel() {
        String content = """
                Parcel type 7""";
        assertThat(parcelParser.parse(content)).containsExactly(
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).name("Parcel type 7").build()
        );
    }

    @Test
    public void parseManyParcels() {
        String content = "Parcel type 7\\nParcel type 9\\nParcel type 1";
        assertThat(parcelParser.parse(content)).containsExactlyInAnyOrder(
                Parcel.builder().form(new Character[][]{{'7', '7', '7', '7'}, {'7', '7', '7'}}).width(4).height(2).name("Parcel type 7").build(),
                Parcel.builder().form(new Character[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}).width(3).height(3).name("Parcel type 9").build(),
                Parcel.builder().form(new Character[][]{{'1'}}).width(1).height(1).name("Parcel type 1").build()
        );
    }

    @Test
    public void parseEmptyContent() {
        assertThat(parcelParser.parse("")).isEqualTo(null);
    }

    @Test
    public void parseIncorrectContent() {
        String content = """
                000""";
        assertThrows(ParcelNotFoundException.class, () -> parcelParser.parse(content));
    }
}