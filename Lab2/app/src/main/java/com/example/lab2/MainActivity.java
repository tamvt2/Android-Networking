package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvView;
    Button btnNext;
    String trang_web = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        trang_web = "https://0e29p7-8080.csb.app/users";
        tvView = findViewById(R.id.tvView);
        btnNext = findViewById(R.id.btnNext);
        new DemoHttpGet(tvView, MainActivity.this).execute(trang_web);

        btnNext.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        });
    }
}