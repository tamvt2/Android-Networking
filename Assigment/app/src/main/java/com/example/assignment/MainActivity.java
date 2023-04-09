package com.example.assignment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.assignment.Adapter.Slider_Adapter;
import com.example.assignment.Adapter.Truyentranh_Adapter;
import com.example.assignment.Model.TruyenTranh;
import com.example.assignment.api.ApiLayTruyen;
import com.example.assignment.interfaces.LayTruyenVe;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LayTruyenVe {
    GridView gridView;
    SliderView sliderView;
    int[] image_slide = {R.drawable.img_1, R.drawable.img_2, R.drawable.img_3, R.drawable.img_4, R.drawable.img_5, R.drawable.img_6};
    Truyentranh_Adapter adapter;
    ArrayList<TruyenTranh> truyenTranhArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid_view);
        truyenTranhArrayList = new ArrayList<>();
        sliderView = findViewById(R.id.slide_view);
        Slider_Adapter slider_adapter = new Slider_Adapter(image_slide);
        sliderView.setSliderAdapter(slider_adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TruyenTranh truyenTranh = truyenTranhArrayList.get(position);
                Bundle b = new Bundle();
                b.putSerializable("truyen", truyenTranh);
                Intent intent = new Intent(getApplicationContext(), TruyenActivity.class);
                intent.putExtra("data", b);
                startActivity(intent);
            }
        });

        new ApiLayTruyen(this).execute();
    }

    @Override
    public void batDau() {
        Toast.makeText(this, "Đang lấy về", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data) {
        try {
            truyenTranhArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                truyenTranhArrayList.add(new TruyenTranh(obj));
            }
            adapter = new Truyentranh_Adapter(this, 0 , truyenTranhArrayList);
            gridView.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void loi() {
        Toast.makeText(this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }
}