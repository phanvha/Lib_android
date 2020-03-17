package com.map4d.lib_test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

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
    SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String jsonobject = saveJsonFileToLocal(MainActivity.this).toString();
//        Log.e("json", jsonobject);
        //saveJsonToSQLite(MainActivity.this);
//        if (db.getCountTotalListVmapCodeTB()!=0){
//            Log.e("countdb", ""+db.getCountTotalListVmapCodeTB());
//        }else{
//            saveJsonToSQLite(MainActivity.this);
//        }


        new saveJsonFileToLocal().execute();

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
            SmartCodeLib.saveJsonToSQLite(MainActivity.this);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("address",SmartCodeLib.getAllDataFromSQLite(MainActivity.this)+"");
            dialog.dismiss();
        }
    }





    private JSONArray parseJsonArray(Context context) {
        InputStream is = context.getResources().openRawResource(R.raw.vmapcodejs);
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
        Log.e("jsonArr", jsonArray.toString());

        return jsonArray;
    }

    private void saveJsonToSQLite(Context context){
        try {
            JSONArray jsonArray = parseJsonArray(context);
            for (int i = 0 ; i<=jsonArray.length(); i++){
                JSONObject jb = jsonArray.getJSONObject(i);
                String id = jb.getString("id");
                String address = jb.getString("address");
                String code = jb.getString("code");
                String doiTuongGanMa = jb.getString("doiTuongGanMa");
                String isDeleted = jb.getString("isDeleted");
                Double latitude = jb.getDouble("latitude");
                Double longitude = jb.getDouble("longitude");
                String maBuuChinh = jb.getString("maBuuChinh");
                String maHuyen = jb.getString("maHuyen");
                String maTinh = jb.getString("maTinh");
                String tenHuyen = jb.getString("tenHuyen");
                String tenTinh = jb.getString("tenTinh");
                Log.e("model_vmapcode1", code+"");
                model_vmapCode_jsons.add(new Model_vmapCode_Json(id, address, code, doiTuongGanMa, isDeleted, latitude, longitude, maBuuChinh, maHuyen, maTinh, tenHuyen, tenTinh));
                Log.e("model_vmapcode", model_vmapCode_jsons.get(i).getAddress()+"");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        db = SQLite.getInstance(context);
//        if (model_vmapCode_jsons!=null){
//            for (int i = model_vmapCode_jsons.size() - 1; i>=0; --i){
//                db.insertListBusStopTB(new Model_vmapCode_Json(
//                        model_vmapCode_jsons.get(i).getId(),
//                        model_vmapCode_jsons.get(i).getAddress(),
//                        model_vmapCode_jsons.get(i).getCode(),
//                        model_vmapCode_jsons.get(i).getDoiTuongGanMa(),
//                        model_vmapCode_jsons.get(i).getDeleted(),
//                        model_vmapCode_jsons.get(i).getLatitude(),
//                        model_vmapCode_jsons.get(i).getLongitude(),
//                        model_vmapCode_jsons.get(i).getMaBuuChinh(),
//                        model_vmapCode_jsons.get(i).getMaHuyen(),
//                        model_vmapCode_jsons.get(i).getMaTinh(),
//                        model_vmapCode_jsons.get(i).getTenHuyen(),
//                        model_vmapCode_jsons.get(i).getTenTinh()));
//            }
//        }
//        Log.e("list_VmapCODE:", db.getCountTotalListVmapCodeTB()+"");
    }
}
