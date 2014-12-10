package prafulmantale.listexplore.models;

import java.io.Serializable;

/**
 * Created by prafulmantale on 8/9/14.
 */
public class TwoLineRowModel implements Serializable{

    private String line1;
    private String line2;

    public TwoLineRowModel(String line1, String line2) {
        this.line1 = line1;
        this.line2 = line2;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }
}
