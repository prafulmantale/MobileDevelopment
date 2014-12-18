package prafulmantale.praful.com.yaym.databases;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import prafulmantale.praful.com.yaym.models.ReferenceData;

/**
 * Created by prafulmantale on 12/18/14.
 */
public class YMDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ym.db";
    private static final String TABLE_HIST_RATE = "hist_rate";
    private static final String TABLE_HIST_YIELD = "hist_yield";
    private static final String TABLE_CCY_PAIR_REF_DATA = "ccy_ref_data";

    //CCY_REF_DATA
    private static final String CRD_COLUMN_INSTRUMENT = "instr";
    private static final String CRD_COLUMN_SPOT_PREC = "sp";
    private static final String CRD_COLUMN_SPOT_POINTS_PREC = "spp";
    private static final String CRD_COLUMN_PIPS_FACTOR = "pf";

    public YMDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public YMDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println("DB onCreate...");
        //Create tables
        createCCyPairRefDataTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        System.out.println("DB onUpdate...");
        if(newVersion == 1){

            //Drop tables
            dropCCyPairRefDataTable(db);


            onCreate(db);
        }
    }

    private void createCCyPairRefDataTable(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_CCY_PAIR_REF_DATA + " ("
                + CRD_COLUMN_INSTRUMENT + " STRING PRIMARY KEY, "
                + CRD_COLUMN_SPOT_PREC + " INTEGER, "
                + CRD_COLUMN_SPOT_POINTS_PREC + " INTEGER, "
                + CRD_COLUMN_PIPS_FACTOR + " INTEGER"
                + ")";

        db.execSQL(createTable);
    }

    private void dropCCyPairRefDataTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CCY_PAIR_REF_DATA);
    }

    public void insertCcyPairRefData(List<ReferenceData> referenceDataList){


    }
    public void insertCcyPairRefData(ReferenceData referenceData){

    }
}
