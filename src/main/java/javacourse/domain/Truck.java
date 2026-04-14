package javacourse.domain;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * Truck model
 */
@Data
@Builder
@Jacksonized
public class Truck {
    private Character[][] truckSpace;
    private int width;
    private int height;
    private List<Parcel> parcels;

    /**
     * String representation of the truck
     * @return string representation of the truck
     */
    public String toStringFormat() {
        StringBuilder str = new StringBuilder();
        String format = "%-" + width + "s";

        for (int i = 0; i < height - truckSpace.length; i++) {
            str.append("+");
            str.append(String.format(format, ' '));
            str.append("+\n");
        }

        for (int i = truckSpace.length - 1; i >= 0; i--) {
            str.append("+");
            for (int j = 0; j < width; j++) {
                if (truckSpace[i].length > j && truckSpace[i][j] != null) {
                    str.append(truckSpace[i][j]);
                } else {
                    str.append(' ');
                }
            }
            str.append("+\n");
        }
        str.append("+");
        str.append("+".repeat(Math.max(0, width)));
        str.append("+\n");

        parcels.stream().map(Parcel::toStringFormat).forEach(str::append);

        return str.toString();
    }
}
