package praful.com.kidsonwheels.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by prafulmantale on 5/17/15.
 */
public class KOWDbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "kow.db";


    public KOWDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAddressTable(db);
        createStudentTable(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Do db migration in case required
        reset();
        onCreate(db);
    }

    public void reset() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + AddressEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + StudentEntry.TABLE_NAME);
    }

    private void createAddressTable(SQLiteDatabase db) {
        final String SQL_CREATE_ADDRESS_TABLE =
                "CREATE TABLE " + AddressEntry.TABLE_NAME + " (" +
                        AddressEntry._ID + " INTEGER PRIMARY KEY," +
                        AddressEntry.COLUMN_PLACE_ID + " TEXT NOT NULL, " +
                        AddressEntry.COLUMN_ADDRESS_STRING + " TEXT NOT NULL, " +
                        AddressEntry.COLUMN_LAT + " REAL NOT NULL, " +
                        AddressEntry.COLUMN_LANG + " REAL NOT NULL " +
                        " UNIQUE " + AddressEntry.COLUMN_PLACE_ID + " ON CONFLICT REPLACE " +
                        " );";
    }

    private void createStudentTable(SQLiteDatabase db) {

        final String SQL_CREATE_STUDENT_TABLE =
                "CREATE TABLE " + StudentEntry.TABLE_NAME + " (" +
                        StudentEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        StudentEntry.COLUMN_ADDRESS_ID + " INTEGER NOT NULL, " +
                        StudentEntry.COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                        StudentEntry.COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                        " FOREIGN KEY (" + StudentEntry.COLUMN_ADDRESS_ID + ") REFERENCES " +
                        AddressEntry.TABLE_NAME + " (" + AddressEntry._ID + ");";

    }

}
