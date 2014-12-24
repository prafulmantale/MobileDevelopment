package prafulmantale.praful.com.yaym.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import prafulmantale.praful.com.yaym.models.HistoricYieldData;
import prafulmantale.praful.com.yaym.models.OHLCData;
import prafulmantale.praful.com.yaym.models.ReferenceData;

/**
 * Created by prafulmantale on 12/18/14.
 */
public class YMDatabase extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "ym.db";


    //CCY_REF_DATA
    private static final String TABLE_CCY_PAIR_REF_DATA = "ccy_ref_data";
    private static final String CRD_COLUMN_INSTRUMENT = "instr";
    private static final String CRD_COLUMN_SPOT_PREC = "sp";
    private static final String CRD_COLUMN_SPOT_POINTS_PREC = "spp";
    private static final String CRD_COLUMN_PIPS_FACTOR = "pf";

    private final String []CRD_COLUMNS = new String[]{CRD_COLUMN_INSTRUMENT, CRD_COLUMN_SPOT_PREC, CRD_COLUMN_SPOT_POINTS_PREC, CRD_COLUMN_PIPS_FACTOR};
    private static final String CRD_SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_CCY_PAIR_REF_DATA;

    //TABLE_HIST_YIELD
    private static final String TABLE_HIST_YIELD = "hist_yield";
    private static final String HY_COLUMN_DONE_PNL = "dpnl";
    private static final String HY_COLUMN_CURRENT_PNL = "cpnl";
    private static final String HY_COLUMN_VOLUME = "vol";
    private static final String HY_COLUMN_CURRENT_YILED = "cyld";
    private static final String HY_COLUMN_TIMESTAMP = "ts";
    private static final String HY_COLUMN_DISPLAY_TIMESTAMP = "dts";

    private final String [] HY_COLUMNS = new String [] {
            CRD_COLUMN_INSTRUMENT, HY_COLUMN_DONE_PNL, HY_COLUMN_CURRENT_PNL,
            HY_COLUMN_VOLUME, HY_COLUMN_CURRENT_YILED, HY_COLUMN_TIMESTAMP, HY_COLUMN_DISPLAY_TIMESTAMP
    };

    private static final String HY_SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_HIST_YIELD;

    //TABLE_HIST_RATE
    private static final String TABLE_HIST_RATE = "hist_rate";
    private static final String HR_COLUMN_OPEN = "or";
    private static final String HR_COLUMN_CLOSE = "oc";
    private static final String HR_COLUMN_HIGH = "oh";
    private static final String HR_COLUMN_LOW = "ol";
    private static final String HR_COLUMN_TIMESTAMP = "ts";
    private static final String HR_COLUMN_DISPLAY_TIMESTAMP = "dts";

    private final String [] HR_COLUMNS = new String [] {
            CRD_COLUMN_INSTRUMENT, HR_COLUMN_OPEN, HR_COLUMN_CLOSE, HR_COLUMN_HIGH, HR_COLUMN_LOW,
            HR_COLUMN_TIMESTAMP, HR_COLUMN_DISPLAY_TIMESTAMP
    };

    private static final String HR_SELECT_ALL_QUERY = "SELECT * FROM " + TABLE_HIST_RATE;


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
        if (newVersion == 1) {

            //Drop tables
            dropCCyPairRefDataTable(db);


            onCreate(db);
        }
    }

    private void createCCyPairRefDataTable(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CCY_PAIR_REF_DATA + " ("
                + CRD_COLUMNS[0] + " STRING PRIMARY KEY, "
                + CRD_COLUMNS[1] + " INTEGER, "
                + CRD_COLUMNS[2] + " INTEGER, "
                + CRD_COLUMNS[3] + " INTEGER"
                + ")";

        db.execSQL(createTable);
    }

    private void createHistYieldTable(SQLiteDatabase db) {

    }

    private void createHistRateTable(SQLiteDatabase db) {

    }

    private void dropCCyPairRefDataTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CCY_PAIR_REF_DATA);
    }

    private void dropHistYieldTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST_YIELD);
    }

    private void dropHistRateTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIST_RATE);
    }

    public void insertCcyPairRefData(List<ReferenceData> referenceDataList) {

        for (ReferenceData referenceData : referenceDataList) {
            insertCcyPairRefData(referenceData);
        }

    }

    public void insertCcyPairRefData(ReferenceData referenceData) {

        SQLiteDatabase db = getWritableDatabase();


        db.insert(TABLE_CCY_PAIR_REF_DATA, null, getRefDataContentValues(referenceData));

        db.close();
    }

    public List<ReferenceData> getAllReferenceData() {

        List<ReferenceData> list = new ArrayList<ReferenceData>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(CRD_SELECT_ALL_QUERY, null);

        if (cursor.moveToFirst()) {
            do {
                ReferenceData refData = getRefData(cursor);
                if (refData != null) {
                    list.add(refData);
                    System.out.println("Read from DB: " + refData);
                }
            } while (cursor.moveToNext());
        }

        db.close();

        return list;
    }

    public ReferenceData getReferenceData(String ccyPair) {

        ReferenceData referenceData = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_CCY_PAIR_REF_DATA,
                CRD_COLUMNS,
                CRD_COLUMN_INSTRUMENT + "= ?", new String[]{ccyPair},
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            referenceData = getRefData(cursor);
        }

        db.close();
        return referenceData;
    }

    public int updateReferenceData(ReferenceData refData) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = getRefDataContentValues(refData);

        int result = db.update(TABLE_CCY_PAIR_REF_DATA,
                contentValues,
                CRD_COLUMN_INSTRUMENT + " = ?",
                new String[]{refData.getInstrument()}
                );

        db.close();

        return result;
    }

    public void deleteReferenceData(ReferenceData referenceData){

        deleteReferenceData(referenceData.getInstrument());
    }

    public void deleteReferenceData(String ccyPair){

        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_CCY_PAIR_REF_DATA, CRD_COLUMN_INSTRUMENT + " = ?", new String[]{ccyPair});

        db.close();
    }

    private ReferenceData getRefData(Cursor cursor) {
        try {
            ReferenceData referenceData = new ReferenceData();

            referenceData.setInstrument(cursor.getString(0));
            referenceData.setSpotPrecision(cursor.getInt(1));
            referenceData.setSpotPointsPrecision(cursor.getInt(2));
            referenceData.setPipsFactor(cursor.getInt(3));

            return referenceData;
        } catch (Exception ex) {
            return null;
        }
    }

    private HistoricYieldData getYieldData(Cursor cursor) {
        try {
            HistoricYieldData data = new HistoricYieldData();

            int counter = 0;
            data.setInstrument(cursor.getString(counter));
            data.setDonePnL(cursor.getDouble(counter++));
            data.setCurrentPnL(cursor.getDouble(counter++));
            data.setDoneVolume(cursor.getDouble(counter++));
            data.setCurrentYield(cursor.getDouble(counter++));
            data.setTimestamp(cursor.getLong(counter++));
            data.setDisplayTimestamp(cursor.getString(counter++));

            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    private OHLCData getOHLCData(Cursor cursor){

        try{

            OHLCData data = new OHLCData();

            int counter = 0;

            data.setInstrument(cursor.getString(counter++));
            data.setOpen(cursor.getDouble(counter++));
            data.setClose(cursor.getDouble(counter++));
            data.setHigh(cursor.getDouble(counter++));
            data.setLow(cursor.getDouble(counter++));
            data.setTimestamp(cursor.getLong(counter++));
            data.setDisplayTimestamp(cursor.getString(counter++));

            return data;
        }
        catch (Exception ex){
            return null;
        }
    }

    private ContentValues getRefDataContentValues(ReferenceData referenceData) {

        ContentValues values = new ContentValues();
        values.put(CRD_COLUMNS[0], referenceData.getInstrument());
        values.put(CRD_COLUMNS[1], referenceData.getSpotPrecision());
        values.put(CRD_COLUMNS[2], referenceData.getSpotPointsPrecision());
        values.put(CRD_COLUMNS[3], (int) referenceData.getPipsFactor());

        return values;
    }

    private ContentValues getYieldContentValues(HistoricYieldData data){
        ContentValues values = new ContentValues();

        values.put(CRD_COLUMN_INSTRUMENT, data.getInstrument());
        values.put(HY_COLUMN_DONE_PNL, data.getDonePnL());
        values.put(HY_COLUMN_CURRENT_PNL, data.getCurrentPnL());
        values.put(HY_COLUMN_VOLUME, data.getDoneVolume());
        values.put(HY_COLUMN_CURRENT_YILED, data.getCurrentYield());
        values.put(HY_COLUMN_TIMESTAMP, data.getTimestamp());
        values.put(HY_COLUMN_DISPLAY_TIMESTAMP, data.getDisplayTimestamp());

        return values;
    }

    private ContentValues getRateContentValues(OHLCData data){

        ContentValues values = new ContentValues();

        values.put(CRD_COLUMN_INSTRUMENT, data.getInstrument());
        values.put(HR_COLUMN_OPEN, data.getOpen());
        values.put(HR_COLUMN_CLOSE, data.getClose());
        values.put(HR_COLUMN_HIGH, data.getHigh());
        values.put(HR_COLUMN_LOW, data.getLow());
        values.put(HR_COLUMN_TIMESTAMP, data.getTimestamp());
        values.put(HR_COLUMN_DISPLAY_TIMESTAMP, data.getDisplayTimestamp());

        return values;
    }
}
