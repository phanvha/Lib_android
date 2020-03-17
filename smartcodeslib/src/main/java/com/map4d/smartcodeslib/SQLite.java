package com.map4d.smartcodeslib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLite extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_SmartCodes";
    private static final int DATABASE_VERSION = 1;

    //TABLE COUNTRY
    private static final String TABLE_VMAPCODE = "Table_VmapCode";
    private static final String ID = "id";
    private static final String IS_DELETED = "is_Deleted";
    private static final String ADDRESS = "address";
    private static final String DOI_TUONG_GAN_MA = "doiTuongGanMa";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String MA_BUU_CHINH = "maBuuChinh";
    private static final String MA_HUYEN = "maHuyen";
    private static final String MA_TINH = "maTinh";
    private static final String CODE = "Code";
    private static final String TEN_HUYEN = "tenHuyen";
    private static final String TEN_TINH = "tenTinh";
    private static final String CREATE_TABLE_COUNTRY= "CREATE TABLE " + TABLE_VMAPCODE + " (" +
            ID + " TEXT PRIMARY KEY NOT NULL, " +
            ADDRESS + " TEXT, " +
            CODE + " TEXT, " +
            DOI_TUONG_GAN_MA + " TEXT, " +
            IS_DELETED + " TEXT, " +
            LATITUDE + " DOUBLE, " +
            LONGITUDE + " DOUBLE, " +
            MA_BUU_CHINH + " TEXT, " +
            MA_HUYEN + " TEXT, " +
            MA_TINH + " TEXT, " +
            TEN_HUYEN + " TEXT,"+
            TEN_TINH + " TEXT " +
            ")";

    public SQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static SQLite sInstance;
    public static SQLite getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SQLite(context.getApplicationContext());
        }
        return sInstance;
    }

    private SQLite(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VMAPCODE);
        onCreate(db);
    }

    public boolean insertListBusStopTB(Model_vmapCode_Json data) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, data.getId());
        values.put(ADDRESS, data.getAddress());
        values.put(CODE, data.getCode());
        values.put(DOI_TUONG_GAN_MA, data.getDoiTuongGanMa());
        values.put(IS_DELETED, data.getDeleted());
        values.put(LATITUDE, data.getLatitude());
        values.put(LONGITUDE, data.getLongitude());
        values.put(MA_BUU_CHINH, data.getMaBuuChinh());
        values.put(MA_HUYEN, data.getMaHuyen());
        values.put(MA_TINH, data.getMaTinh());
        values.put(TEN_HUYEN, data.getTenHuyen());
        values.put(TEN_TINH, data.getTenTinh());
        long rowId = db.insert(TABLE_VMAPCODE, null, values);
        db.close();
        if (rowId != -1)
            return true;
        return false;
    }
    //get total
    public int getCountTotalListVmapCodeTB() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_VMAPCODE;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }

//    public MetaVersion getVer() {
//        SQLiteDatabase db = getReadableDatabase();
//        MetaVersion words = new MetaVersion();
//        String sql = "SELECT * FROM " + TABLE_VERSION;
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                words = new MetaVersion(cursor.getString(1),cursor.getInt(2), cursor.getString(3),cursor.getString(4));
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//
//        db.close();
//        return words;
//    }

    public String getAll() {
        String address;
        SQLiteDatabase db = getReadableDatabase();
        List<Model_Table_Vmapcode> words = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_VMAPCODE;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                words.add(new Model_Table_Vmapcode(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getDouble(5),
                        cursor.getDouble(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getString(10),
                        cursor.getString(11)
                ));
            } while (cursor.moveToNext());

            cursor.close();
        }
        address = words.get(1).getAddress();
        db.close();
        return address;
    }

//    //get all data version
//    public List<MetaVersion> getAllInDataVersionTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        List<MetaVersion> words = new ArrayList<>();
//        String sql = "SELECT * FROM " + TABLE_VERSION;
//        Cursor cursor = db.rawQuery(sql, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                words.add(new MetaVersion(
//                        cursor.getString(1),
//                        cursor.getInt(2),
//                        cursor.getString(3),
//                        cursor.getString(4)
//                ));
//            } while (cursor.moveToNext());
//            cursor.close();
//        }
//
//        db.close();
//        return words;
//    }
//
//    //get total bus stop
//    public int getCountTotalListBusStopTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_LIST_BUS_STOP;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//    //get total route
//    public int getCountTotalListRouteFindDataTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_LIST_ROUTE_FIND_DATA;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//    //get total row of routedetail_1 table
//    public int getTotalListRouteTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_LIST_ROUTE;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//
//    //get total routedetail_2
//    public int getTotalListBusOnRouteTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_LIST_BUS_STOP_ON_ROUTE;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//    //get total data version
//    public int getTotalVersionTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        String sql = "SELECT * FROM " + TABLE_VERSION;
//        Cursor cursor = db.rawQuery(sql, null);
//        int totalRows = cursor.getCount();
//        cursor.close();
//        return totalRows;
//    }
//    //        public int updateVersion(Data data) {
////        SQLiteDatabase db = getWritableDatabase();
////        ContentValues values = new ContentValues();
////        values.put(ID_COLUMN, data.getId());
////        int rowEffect = db.update(TABLE_POLYGON, values, ID_COLUMN + " = ?", new String[]{data.getName()});
////        db.close();
////        return rowEffect;
////    }
////
//    public int deleteListBusStopTB() {
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE_LIST_BUS_STOP, null, null);
//        db.close();
//        return rowEffect;
//    }
//    public int deleteVersionTB(){
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE_VERSION, null,null);
//        db.close();
//        return rowEffect;
//    }
//    public int deleteListRouteFindDataTB(){
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE_LIST_ROUTE_FIND_DATA, null,null);
//        db.close();
//        return rowEffect;
//    }
//    public int deleteRouteTB(){
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE_LIST_ROUTE, null,null);
//        db.close();
//        return rowEffect;
//    }
//    public int deleteListBusOnRouteTB(){
//        SQLiteDatabase db = getReadableDatabase();
//        int rowEffect = db.delete(TABLE_LIST_BUS_STOP_ON_ROUTE, null,null);
//        db.close();
//        return rowEffect;
//    }
//
//    //VERSION
//    private boolean insertVersionAPI(VersionAPI data){
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(ID_CL, data.getData().id);
//        values.put(VERSIONDATA_CL, data.getData().VersionData);
//        values.put(UPDATE_DATE_CL, data.getData().UpdateDate);
//        values.put(VERSIONDATA_CL, data.getData().VersionApi);
//        long rowId = db.insert(TABLE_VERSION, null, values);
//        db.close();
//        if (rowId != -1)
//            return true;
//        return false;
//    }


}

