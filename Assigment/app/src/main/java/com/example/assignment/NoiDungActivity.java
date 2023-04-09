package com.example.assignment;

import static com.example.assignment.LoginActivity.IP;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Adapter.Content_Adapter;
import com.example.assignment.Model.Content;
import com.example.assignment.api.ApiLayContent;
import com.example.assignment.interfaces.LayCommentVe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NoiDungActivity extends AppCompatActivity implements LayCommentVe {
    Content_Adapter adapter;
    ArrayList<Content> arrayList;
    ListView listView;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noi_dung);
        Bundle bundle = getIntent().getExtras();
        String idTruyen = bundle.getString("data");
        listView = findViewById(R.id.lv_content);
        img = findViewById(R.id.img_back);
        arrayList = new ArrayList<>();

        new ApiLayContent(this).execute("http://"+IP+":3000/api/content/"+idTruyen);

        img.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), TruyenActivity.class));
        });
    }

    @Override
    public void ketThuc(String data) {
        try {
            arrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                arrayList.add(new Content(obj));
                Log.d("shdb", "img: "+obj);
            }
            adapter = new Content_Adapter(this, 0 , arrayList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void loi() {
        Toast.makeText(this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }
}