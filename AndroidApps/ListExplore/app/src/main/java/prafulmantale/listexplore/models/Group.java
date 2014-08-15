package prafulmantale.listexplore.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prafulmantale on 8/15/14.
 */
public class Group {

    private String header;
    private final List<String> children;

    public Group(String header) {
        this.header = header;
        children = new ArrayList<String>();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<String> getChildren() {
        return children;
    }

//    public void setChildren(List<String> children) {
//        this.children = children;
//    }
}
