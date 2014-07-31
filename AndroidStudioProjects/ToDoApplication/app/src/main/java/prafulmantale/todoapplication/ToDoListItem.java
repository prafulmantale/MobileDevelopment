package prafulmantale.todoapplication;

import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by prafulmantale on 7/29/14.
 */
public class ToDoListItem {

    private String item;

    public ToDoListItem(String item) {
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
