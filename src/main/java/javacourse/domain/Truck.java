package javacourse.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Truck {
    private char[][] truckSpace;
    private int width;
    private int height;

    @Override
    public String toString() {
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
                if (truckSpace[i].length > j && truckSpace[i][j] != '\u0000') {
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
        return str.toString();
    }
}
