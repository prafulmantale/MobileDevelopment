package prafulmantale.simpletodolist.models;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by prafulmantale on 7/31/14.
 */
public class ToDoItem implements Serializable{

    public enum ItemPriority{
        None(0),
        First(1),
        Second(2),
        Third(3);

        private final int priority;

        ItemPriority(int priority){
         this.priority = priority;
        }

        public int getValue(){
            return priority;
        }
    }

    private long id;
    private String item;
    private boolean isCompleted;
    private boolean isDueDateConfigured;
    private Date dueDate;
    private ItemPriority priority;

    public ToDoItem(){
        item = null;
        isCompleted = false;
        isDueDateConfigured = false;
        dueDate = new Date();
        priority = ItemPriority.None;
    }

    public ToDoItem(String item) {
        this.item = item;
        isCompleted = false;
        isDueDateConfigured = false;
        dueDate = new Date();
        priority = ItemPriority.None;
        System.out.println("priority: " + priority.getValue());
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

    public ItemPriority getPriority() {
        return priority;
    }

    public void setPriority(ItemPriority priority) {
        this.priority = priority;
    }

    public void setPriority(int priority){

        if(priority == 0){
            this.priority = ItemPriority.None;
        }
        else if(priority == 1){
            this.priority = ItemPriority.First;
        }
        else if (priority == 2){
            this.priority = ItemPriority.Second;
        }
        else if (priority == 3){
            this.priority = ItemPriority.Third;
        }
    }

    public String getDateTime(){
        if(!isDueDateConfigured){
            return "";
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        return simpleDateFormat.format(dueDate);
    }

    public void setDateTime(String input){
        if(!isDueDateConfigured){
            return;
        }

        try {
            setDueDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(input));
        }
        catch (ParseException ex) {

        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(100);

        sb.append("Item: ").append(item).append("  ").append("Completed").append(isCompleted);
        if(!isCompleted()){
            if(isDueDateConfigured) {
                sb.append("   Due date: ").append(getDateTime());
            }
            else{
                sb.append("   No Due date configured").append(getDateTime());
            }

            sb.append("  Priority: ").append(getPriority());
        }


        return sb.toString();
    }
}
