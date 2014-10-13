package prafulmantale.praful.com.staggeredgridviewsample;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ToDoItem implements Serializable {

    private String item;

    public ToDoItem(){
        item = null;
    }

    public ToDoItem(String item) {
        this.item = item;
    }


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                "item='" + item + '\'' +
                '}';
    }
}
