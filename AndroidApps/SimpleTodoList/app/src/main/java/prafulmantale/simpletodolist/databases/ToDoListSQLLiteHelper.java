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
    private static final String COLUMN_COMPLETED = "COMPLETED";
    private static final String DB_NAME = "itemslist.db";
    private static final int DB_VERSION = 2;

    private static final String DB_CREATE_SQL ="create table " + TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_ITEM + " text not null, " + COLUMN_COMPLETED + " integer );";


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
        return new String[]{COLUMN_ID, COLUMN_ITEM, COLUMN_COMPLETED};
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

}
