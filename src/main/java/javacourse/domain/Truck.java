package javacourse.domain;

import lombok.Builder;

@Builder
public class Truck {
    public String Level1;
    public String Level2;
    public String Level3;
    public String Level4;
    public String Level5;
    public String Level6;

    @Override
    public String toString() {
        return "+" + this.Level6 + "+\n"
                + "+" + this.Level5 + "+\n"
                + "+" + this.Level4 + "+\n"
                + "+" + this.Level3 + "+\n"
                + "+" + this.Level2 + "+\n"
                + "+" + this.Level1 + "+\n"
                + "++++++++\n";
    }
}
