package prafulmantale.simpletodolist.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by prafulmantale on 8/1/14.
 */
public class ToDoListSQLLiteHelper  extends SQLiteOpenHelper{

    private static final String TABLE_NAME = "todoitemslist";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_ITEM = "item";
    private static final String COLUMN_COMPLETED = "completed";
    private static final String COLUMN_DUE_DATE_CONFIGURED = "duedateconfigured";
    private static final String COLUMN_DUE_DATE = "duedate";
    private static final String COLUMN_PRIORITY = "priority";

    private static final String DB_NAME = "itemslist.db";
    private static final int DB_VERSION = 4;

    private static final String DB_CREATE_SQL ="create table " + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ITEM + " text not null, " + COLUMN_COMPLETED + " integer, " + COLUMN_PRIORITY + " integer DEFAULT 0, "
            + COLUMN_DUE_DATE_CONFIGURED + " integer, " + COLUMN_DUE_DATE + " text "
            + ");";


    public ToDoListSQLLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(DB_CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        Log.w(ToDoListSQLLiteHelper.class.getName(), " Upgrading database from version " + oldVersion +
            " to version " + newVersion + " , this will destroy existing data");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public String [] getColumns(){
        return new String[]{COLUMN_ID, COLUMN_ITEM, COLUMN_COMPLETED, COLUMN_PRIORITY, COLUMN_DUE_DATE_CONFIGURED, COLUMN_DUE_DATE};
    }

    public String getColumnItem(){
        return COLUMN_ITEM;
    }

    public String getTableName(){
        return TABLE_NAME;
    }

    public String getColumnId(){
        return COLUMN_ID;
    }

    public String getColumnCompleted(){
        return COLUMN_COMPLETED;
    }

    public String getColumnDueDateConfigured(){
        return COLUMN_DUE_DATE_CONFIGURED;
    }

    public String getColumnDueDate(){
        return COLUMN_DUE_DATE;
    }

    public String getColumnPriority(){
        return COLUMN_PRIORITY;
    }

}
