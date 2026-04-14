package javacourse.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.Arrays;

/**
 * Parcel model
 */
@Data
@Builder
@Jacksonized
public class Parcel {
    private int height;
    private int width;
    private Character[][] form;
    private String name;

    /**
     * String representation of the parcel
     * @return string representation of the parcel
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("name: \"").append(name).append("\"\n");
        str.append("form:\n");
        for (int i = form.length - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                if (form[i].length > j && form[i][j] != null) {
                    str.append(form[i][j]);
                }
            }
            str.append("\n");
        }
        return str.toString();
    }

    /**
     * String representation of the parcel for truck
     * @return string representation of the parcel for truck
     */
    public String toStringFormat() {
        return name + ":\n" +
                Arrays.deepToString(form) + "\n" + "---------------\n";
    }
}
