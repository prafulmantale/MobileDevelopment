package prafulmantale.simpletodolist.models;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItem {

    private long id;
    private String item;
    private boolean isCompleted;
    private boolean isSelected;

    public ToDoItem(){
        item = null;
        isCompleted = false;
        isSelected = false;
    }
    public ToDoItem(String item) {
        this.item = item;
        isCompleted = false;
        isSelected = false;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public String toString(){
        return "Item: " + item + " Completed: " + isCompleted;
    }
}
