package javacourse.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parcel {
    private int height;
    private int width;
    private String[][] form;
}
