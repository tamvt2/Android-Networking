package com.example.demo3_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Dangnhap extends AppCompatActivity {

    EditText username,password;
    Button btn_ok;
    ImageView back;
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        username = findViewById(R.id.edt_username);
        password = findViewById(R.id.edt_password);
        btn_ok = findViewById(R.id.btn_ok);
        back = findViewById(R.id.img_back);


        String duong_dan = "https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users";
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(Dangnhap.this, "Vui long dien day du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    new XuLyDangNhap(username,password,Dangnhap.this).execute(duong_dan);

                }

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dangnhap.this,Vidu2.class));
            }
        });
    }
}
