package prafulmantale.praful.com.newtodolist;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItem implements Serializable {




    private String item;
    private boolean isCompleted;


    public ToDoItem(){
        item = null;
        isCompleted = false;

    }

    public ToDoItem(String item) {
        this.item = item;
        isCompleted = false;

    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
