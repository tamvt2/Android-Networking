package com.example.demo3_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        String TAG = "rrrrrrrrr";
        Button next;
        ListView listView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = findViewById(R.id.lv_dautien);
            next = findViewById(R.id.btnnextpage);

            String url = "https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/demo";
            new Get_Http(listView,MainActivity.this).execute(url);

            // chuyển dữ liệu từ chuỗi thành json
//            String chuoi_Json = "{\"id\":1, \"name\":\"Dien thoai\", \"prices\":12000}";
//            Log.d(TAG,"onCreate: Chuoi Json: "+chuoi_Json);
//
//            //chuyen chuoi thanh doi tuong
//            try {
//                JSONObject jsonObject = new JSONObject(chuoi_Json);
//                Log.d(TAG, "onCreate: JD = "+ jsonObject.get("id"));
//                Log.d(TAG, "onCreate: Name = "+ jsonObject.get("name"));
//                Log.d(TAG, "onCreate: Price = "+ jsonObject.get("prices"));
//
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }

            // Làm việc với mảng JSON
//            String mangr_son = "[{\"id\":1, \"name\":\"Dien thoai\", \"prices\":12000},{\"id\":2, \"name\":\"May Tinh\", \"prices\":13000}]";
//
//           try {
//               JSONArray array = new JSONArray(mangr_son);
//               for (int i =0; i<array.length();i++){
//                   JSONObject obj = array.getJSONObject(i);
//                   Log.d(TAG, "onCreate: JD = "+ obj.get("id"));
//                Log.d(TAG, "onCreate: Name = "+ obj.get("name"));
//                Log.d(TAG, "onCreate: Price = "+ obj.get("prices"));
//                Log.d(TAG, "chuyen ve chuoi = "+ obj.toString());
//               }
//           }catch (JSONException e){
//               e.printStackTrace();
//           }



            //===========Lam viec vowi Gson=============

//            DTO user = new DTO();
//            user.id= 1;
//            user.name = " le van nam";
//            Gson gson = new Gson();
//            String chuoi_json = gson.toJson(user);
//            Log.d(TAG, "chuyen ve chuoi = "+ chuoi_json);
//
//
//            // chuyen tu chuoi JSON thanh doi tuong java
//
//            String chuoi_Json1 = "{\"id\":1, \"name\":\"Ban Nam dau ten\"}";
//            DTO user1 = gson.fromJson(chuoi_Json1,DTO.class);
//            Log.d(TAG, "chuyen ve doi tuong java: "+ user1.name);

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this,Vidu2.class));
                }
            });


        }
}
