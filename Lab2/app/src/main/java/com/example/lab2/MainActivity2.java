package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    Button btnSave;
    EditText username, pass, email;
    String trang_web = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        trang_web = "https://0e29p7-8080.csb.app/users";
        //new DemoHttpPost().execute(web);

        username = findViewById(R.id.username);
        pass = findViewById(R.id.pass);
        email = findViewById(R.id.email);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().trim().isEmpty() || pass.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity2.this, "Moi nhap lieu", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    new DemoHttpPost(username.getText().toString().trim(), pass.getText().toString().trim(), email.getText().toString().trim(), MainActivity2.this).execute(trang_web);
                }
            }
        });

    }
}