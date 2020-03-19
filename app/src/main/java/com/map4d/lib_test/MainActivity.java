package com.map4d.lib_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.map4d.smartcodeslib.SmartCodeLib;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String json;
    private JSONArray jsonArray;
    private List<Model_vmapCode_Json> model_vmapCode_jsons = new ArrayList<>();
    private List<Model_geometry> model_geometries = new ArrayList<>();
    SQLite db;
    private List<Model_Geometry_tb> model_geometry_tbs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String jsonobject = parseJsonArray(MainActivity.this).toString();
//        Log.e("json", jsonobject);
        //saveJsonToSQLite(MainActivity.this);
//        if (db.getCountTotalListVmapCodeTB()!=0){
//            Log.e("countdb", ""+db.getCountTotalListVmapCodeTB());
//        }else{
//            saveJsonToSQLite(MainActivity.this);
//        }

        saveJsonToSQLite(MainActivity.this);


        //new saveJsonFileToLocal().execute();

    }
    class saveJsonFileToLocal extends AsyncTask<Void, Void, String> {
        // private ProgressDialog dialog;
        private ProgressDialog dialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            // This progressbar will load util tast in doInBackground method loads
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Đang giải nén...");
            dialog.setCancelable(true);
            dialog.setTitle("Dữ liệu");
            dialog.setMax(100);
            dialog.show();

        }

        @Override
        protected String doInBackground(Void... params) {
            SmartCodeLib.saveJsonToVmapCodeTB(MainActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("jsonARR",SmartCodeLib.getJsonArrayFromVmapCodeTable(MainActivity.this).toString());
            dialog.dismiss();
        }
    }





    private JSONArray parseJsonArray(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.geometry_min);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        json = writer.toString();
        try {
            jsonArray = new JSONArray(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.e("jsonArr", jsonArray.toString());

        return jsonArray;
    }

    private void saveJsonToSQLite(Context context){
        try {
            model_geometry_tbs = new ArrayList<>();
            JSONArray jsonArray = parseJsonArray(context);
            for (int i = 0 ; i<=jsonArray.length(); i++){
                JSONObject jb = jsonArray.getJSONObject(i);
                String id = jb.getString("_id");
                String code = jb.getString("code");
                Boolean isDeleted = jb.getBoolean("isDeleted");
                JSONObject geometry = jb.getJSONObject("geometry");
                String type = geometry.getString("type");
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                Log.e("code", code+"");
                model_geometry_tbs.add(new Model_Geometry_tb(id, code, type, coordinates.toString(), isDeleted));



                //Log.e("arr", array.toString()+"");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        db = SQLite.getInstance(context);
        for (int i = model_geometry_tbs.size() -1; i>=0; --i){
            Log.e("dataa", model_geometry_tbs.get(i).getId()+" "+model_geometry_tbs.get(i).getCode().toString()+"");

        }

    }
}
