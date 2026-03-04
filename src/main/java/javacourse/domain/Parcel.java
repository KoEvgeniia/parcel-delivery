package javacourse.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parcel {
    private String level1;
    private String level2;
    private String level3;
    private int height;
    private int width;
}
