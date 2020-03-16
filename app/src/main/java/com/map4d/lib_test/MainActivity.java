package com.map4d.lib_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;


public class MainActivity extends AppCompatActivity {

    private String json;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String jsonobject = saveJsonFileToLocal(MainActivity.this).toString();
//        Log.e("json", jsonobject);

    }
//    private JSONArray saveJsonFileToLocal(Context context) {
//        InputStream is = context.getResources().openRawResource(R.raw.vmapcodejs);
//        Writer writer = new StringWriter();
//        char[] buffer = new char[1024];
//        try {
//            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            int n;
//            while ((n = reader.read(buffer)) != -1) {
//                writer.write(buffer, 0, n);
//            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        json = writer.toString();
//        try {
//            jsonArray = new JSONArray(json);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Log.e("jsonArr", jsonArray.toString());
//        return jsonArray;
//    }
}
