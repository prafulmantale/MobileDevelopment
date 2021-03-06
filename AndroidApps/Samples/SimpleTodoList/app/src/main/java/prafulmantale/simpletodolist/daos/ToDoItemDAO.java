package prafulmantale.simpletodolist.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.simpletodolist.databases.ToDoListSQLLiteHelper;
import prafulmantale.simpletodolist.models.ToDoItem;

/**
 * Created by prafulmantale on 8/1/14.
 */
public class ToDoItemDAO {

    private SQLiteDatabase database;
    private ToDoListSQLLiteHelper dbHelper;
    private String[] columns;

    public ToDoItemDAO(Context context){
        dbHelper = new ToDoListSQLLiteHelper(context);
        columns = dbHelper.getColumns();
    }

    public void openConnection() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void closeConnection(){
        if(database != null){
            dbHelper.close();
        }
    }

    public ToDoItem createToDoItem(String todoItem){

        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getColumnItem(), todoItem);
        contentValues.put(dbHelper.getColumnCompleted(), 0);
        contentValues.put(dbHelper.getColumnPriority(), 0);
        contentValues.put(dbHelper.getColumnDueDateConfigured(), 0);
        contentValues.put(dbHelper.getColumnDueDate(), "");

        long insertId = database.insert(dbHelper.getTableName(), null, contentValues);

        Cursor cursor = database.query(dbHelper.getTableName(), columns, dbHelper.getColumnId() + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();

        ToDoItem newItem = cursorToModel(cursor);

        cursor.close();

        return newItem;
    }

    public void deleteToDoItem(ToDoItem toDoItem){
        long id = toDoItem.getId();

        database.delete(dbHelper.getTableName(), dbHelper.getColumnId() + " = " + id, null);

        Log.w(ToDoItemDAO.class.getName(), " To Do Item with id " + id + " deleted.");
    }

    public void updateToDoItem(ToDoItem toDoItem){
        long id = toDoItem.getId();
        ContentValues contentValues = new ContentValues();
        contentValues.put(dbHelper.getColumnItem(), toDoItem.getItem());
        contentValues.put(dbHelper.getColumnCompleted(), toDoItem.isCompleted() == true ? 1 : 0);
        contentValues.put(dbHelper.getColumnPriority(), toDoItem.getPriority().getValue());
        contentValues.put(dbHelper.getColumnDueDateConfigured(), toDoItem.isDueDateConfigured() ? 1 : 0);
        contentValues.put(dbHelper.getColumnDueDate(), toDoItem.getDateTime());

        database.update(dbHelper.getTableName(),contentValues,  dbHelper.getColumnId() + " = " + id, null);

        Log.w(ToDoItemDAO.class.getName(), " To Do Item with id " + id + " updated.");
    }

    public List<ToDoItem> getAll(){

        List<ToDoItem> items = new ArrayList<ToDoItem>();

        Cursor cursor = database.query(dbHelper.getTableName(), columns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            items.add(cursorToModel(cursor));
            cursor.moveToNext();
        }

        cursor.close();

        return items;
    }

    private ToDoItem cursorToModel(Cursor cursor){
        ToDoItem toDoItem = new ToDoItem();
        toDoItem.setId(cursor.getInt(0));
        toDoItem.setItem(cursor.getString(1));
        toDoItem.setCompleted(cursor.getInt(2) == 0 ? false : true);
        toDoItem.setPriority(cursor.getInt(3));
        toDoItem.setDueDateConfigured(cursor.getInt(4) == 0 ? false : true);
        toDoItem.setDateTime(cursor.getString(5));

        return toDoItem;
    }

}
