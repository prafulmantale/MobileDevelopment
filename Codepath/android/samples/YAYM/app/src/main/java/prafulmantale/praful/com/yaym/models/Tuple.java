package prafulmantale.praful.com.yaym.models;

import java.io.File;

/**
 * Created by prafulmantale on 10/9/14.
 */
public class Tuple<First, Second> {

    private First first;
    private Second second;

    public Tuple(First first, Second second) {
        this.first = first;
        this.second = second;
    }

    public First getFirst() {
        return first;
    }

    public void setFirst(First first) {
        this.first = first;
    }

    public Second getSecond() {
        return second;
    }

    public void setSecond(Second second) {
        this.second = second;
    }
}
