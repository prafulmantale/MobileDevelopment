package prafulmantale.simpletodolist.models;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItem {

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
}
