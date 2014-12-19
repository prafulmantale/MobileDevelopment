package prafulmantale.praful.com.yaym.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
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


    //CCY_REF_DATA
    private static final String TABLE_CCY_PAIR_REF_DATA = "ccy_ref_data";
    private static final String CRD_COLUMN_INSTRUMENT = "instr";
    private static final String CRD_COLUMN_SPOT_PREC = "sp";
    private static final String CRD_COLUMN_SPOT_POINTS_PREC = "spp";
    private static final String CRD_COLUMN_PIPS_FACTOR = "pf";
    private static final String CRD_SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_CCY_PAIR_REF_DATA;

    public YMDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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

        for(ReferenceData referenceData : referenceDataList){
            insertCcyPairRefData(referenceData);
        }

    }
    public void insertCcyPairRefData(ReferenceData referenceData){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CRD_COLUMN_INSTRUMENT, referenceData.getInstrument());
        values.put(CRD_COLUMN_SPOT_PREC, referenceData.getSpotPrecision());
        values.put(CRD_COLUMN_SPOT_POINTS_PREC, referenceData.getSpotPointsPrecision());
        values.put(CRD_COLUMN_PIPS_FACTOR, (int)referenceData.getPipsFactor());

        db.insert(TABLE_CCY_PAIR_REF_DATA, null, values);
    }

    public List<ReferenceData> getAllReferenceData(){

        List<ReferenceData> list = new ArrayList<ReferenceData>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(CRD_SELECT_ALL_QUERY, null);

        if(cursor.moveToFirst()){
            do{
                ReferenceData referenceData = new ReferenceData();

                referenceData.setInstrument(cursor.getString(0));
                referenceData.setSpotPrecision(cursor.getInt(1));
                referenceData.setSpotPointsPrecision(cursor.getInt(2));
                referenceData.setPipsFactor(cursor.getInt(3));
                list.add(referenceData);
                System.out.println("Read from DB: " + referenceData);

            }while (cursor.moveToNext());
        }

        return list;
    }
}
