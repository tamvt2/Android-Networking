package com.example.demo3_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vidu2 extends AppCompatActivity {

    EditText username,email,pass,link;
    Button btn_dangky,btn_dangnhap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vidu2);

        username = findViewById(R.id.edt_username);
        email = findViewById(R.id.edt_email);
        pass = findViewById(R.id.edt_password);
        link = findViewById(R.id.edt_avata);
        btn_dangky = findViewById(R.id.btn_dangky);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);

        String duongdan = "https://63db6922a3ac95cec5a10e24.mockapi.io/demo-api/users";

        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().isEmpty() || email.getText().toString().trim().isEmpty()
                        || pass.getText().toString().trim().isEmpty() || link.getText().toString().trim().isEmpty()){
                    Toast.makeText(Vidu2.this, "Vui long dien du thong tin", Toast.LENGTH_SHORT).show();

                }else {
                    new Post_Http(username.getText().toString().trim(),pass.getText().toString().trim(),
                            email.getText().toString().trim(),link.getText().toString().trim(),Vidu2.this).execute(duongdan);
                    Toast.makeText(Vidu2.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Vidu2.this,Dangnhap.class));
            }
        });
    }
}
