package javacourse.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Truck {
    private String level1;
    private String level2;
    private String level3;
    private String level4;
    private String level5;
    private String level6;

    @Override
    public String toString() {
        return "+" + this.level6 + "+\n"
                + "+" + this.level5 + "+\n"
                + "+" + this.level4 + "+\n"
                + "+" + this.level3 + "+\n"
                + "+" + this.level2 + "+\n"
                + "+" + this.level1 + "+\n"
                + "++++++++\n";
    }
}
