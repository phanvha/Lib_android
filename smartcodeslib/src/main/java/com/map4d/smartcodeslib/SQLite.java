package com.map4d.smartcodeslib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    //vmapCode Table
    //insert
    public boolean insertDataToVmapTable(Model_vmapCode_Json data) {
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
    //count
    public int getCountTotalListVmapCodeTB() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM " + TABLE_VMAPCODE;
        Cursor cursor = db.rawQuery(sql, null);
        int totalRows = cursor.getCount();
        cursor.close();
        return totalRows;
    }
    //get data jsonArray
    public JSONArray getALLDataFromVmapCodeTable() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
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
        for (int i = words.size() -1;i>=0;--i){
            try {
                jsonObject.put("id", words.get(i).getId());
                jsonObject.put("address", words.get(i).getAddress());
                jsonObject.put("code", words.get(i).getCode());
                jsonObject.put("doiTuongGanMa", words.get(i).getDoiTuongGanMa());
                jsonObject.put("isDeleted", words.get(i).getIsDeleted());
                jsonObject.put("latitude", words.get(i).getLatitude());
                jsonObject.put("longitude", words.get(i).getLongitude());
                jsonObject.put("maBuuChinh", words.get(i).getMaBuuChinh());
                jsonObject.put("maHuyen", words.get(i).getMaHuyen());
                jsonObject.put("maTinh", words.get(i).getMaTinh());
                jsonObject.put("tenHuyen", words.get(i).getTenHuyen());
                jsonObject.put("tenTinh", words.get(i).getTenTinh());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jsonArray.put(jsonObject);
        }
        db.close();
        return jsonArray;
    }
    //delete
    public int deleteVmapCodeTable() {
        SQLiteDatabase db = getReadableDatabase();
        int rowEffect = db.delete(TABLE_VMAPCODE, null, null);
        db.close();
        return rowEffect;
    }

    //Geometry Table



}

