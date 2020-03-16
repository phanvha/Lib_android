package com.map4d.smartcodeslib;

import android.content.Context;
import android.util.Log;


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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmartCodeLib {
    public static Context context;
    public static String json;
    public static Model_Smartcode_Data model_smartcode_data;
    public static List<Model_vmapCode_Json> model_vmapCode_jsons = new ArrayList<>();
    public static JSONObject object;
    public static JSONArray jsonArray;
    public static SQLite db;

    public static JSONObject getSmartcode (Double latitude, Double longitude) {

        String latlng = latitude+","+longitude;
        String versionAPI = "v1.0";
        API_Smartcode_Interface service = API_Smartcode.getClient2().create(API_Smartcode_Interface.class);
        Call<Model_Smartcode_Data> userCall = service.getSmartcodeData(versionAPI, latlng);
        userCall.enqueue(new Callback<Model_Smartcode_Data>() {
            @Override
            public void onResponse(Call<Model_Smartcode_Data> call, Response<Model_Smartcode_Data> response) {
                if (response.isSuccessful()){

                    //min
                    JSONObject min = new JSONObject();
                    try {
                        min.put("lng", response.body().getResults().getMin().getLongitude());
                        min.put("lat", response.body().getResults().getMin().getLatitude());

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //max
                    JSONObject max = new JSONObject();
                    try {
                        max.put("lng", response.body().getResults().getMax().getLongitude());
                        max.put("lat", response.body().getResults().getMax().getLatitude());

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //location
                    JSONObject location = new JSONObject();
                    try {
                        location.put("lng", response.body().getResults().getLocation().getLongitude());
                        location.put("lat", response.body().getResults().getLocation().getLatitude());

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //center
                    JSONObject center = new JSONObject();
                    try {
                        center.put("lng", response.body().getResults().getCenter().getLongitude());
                        center.put("lat", response.body().getResults().getCenter().getLatitude());

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    //result
                    JSONObject result = new JSONObject();
                    try {
                        result.put("min", min);
                        result.put("max", max);
                        result.put("location", location);
                        result.put("compoundCode", response.body().getResults().getCompoundCode());
                        result.put("smartCode", response.body().getResults().getSmartCode());
                        result.put("center", center);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //
                    object = new JSONObject();
                    try {
                        object.put("code", response.body().getCode());
                        object.put("result", result);

                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    Log.e("jsonObject", object.toString());
                }
            }

            @Override
            public void onFailure(Call<Model_Smartcode_Data> call, Throwable t) {

            }
        });
        return object;
    }
    public static int x(int a, int b){
        return a+b;
    }

    public static JSONArray parseJsonArray(Context context) {
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
    public static void saveJsonToSQLite(Context context){
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
                model_vmapCode_jsons.add(new Model_vmapCode_Json(id, address, code, doiTuongGanMa, isDeleted, latitude, longitude, maBuuChinh, maHuyen, maTinh, tenHuyen, tenTinh));

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        db = SQLite.getInstance(context);
        if (model_vmapCode_jsons!=null){
            for (int i = model_vmapCode_jsons.size() - 1; i>=0; --i){
                db.insertListBusStopTB(new Model_vmapCode_Json(
                        model_vmapCode_jsons.get(i).getId(),
                        model_vmapCode_jsons.get(i).getAddress(),
                        model_vmapCode_jsons.get(i).getCode(),
                        model_vmapCode_jsons.get(i).getDoiTuongGanMa(),
                        model_vmapCode_jsons.get(i).getDeleted(),
                        model_vmapCode_jsons.get(i).getLatitude(),
                        model_vmapCode_jsons.get(i).getLongitude(),
                        model_vmapCode_jsons.get(i).getMaBuuChinh(),
                        model_vmapCode_jsons.get(i).getMaHuyen(),
                        model_vmapCode_jsons.get(i).getMaTinh(),
                        model_vmapCode_jsons.get(i).getTenHuyen(),
                        model_vmapCode_jsons.get(i).getTenTinh()));
            }
        }
        Log.e("list_VmapCODE:", db.getCountTotalListVmapCodeTB()+"");
    }

}
