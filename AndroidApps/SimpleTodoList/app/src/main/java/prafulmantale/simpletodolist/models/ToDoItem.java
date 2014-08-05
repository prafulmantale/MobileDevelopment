package prafulmantale.simpletodolist.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItem {

    private long id;
    private String item;
    private boolean isCompleted;
    private boolean isDueDateConfigured;
    private Date dueDate;

    public ToDoItem(){
        item = null;
        isCompleted = false;
        isDueDateConfigured = false;
        dueDate = new Date();
    }
    public ToDoItem(String item) {
        this.item = item;
        isCompleted = false;
        isDueDateConfigured = false;
        dueDate = new Date();
    }

    public long getId(){
        return id;
    }

    public void setId(long  id){
        this.id = id;
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

    public boolean isDueDateConfigured() {
        return isDueDateConfigured;
    }

    public void setDueDateConfigured(boolean isDueDateConfigured) {
        this.isDueDateConfigured = isDueDateConfigured;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDateTime(){
        if(!isDueDateConfigured){
            return "";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return simpleDateFormat.format(dueDate);
    }

    @Override
    public String toString(){
        return "Item: " + item + " Completed: " + isCompleted;
    }
}
