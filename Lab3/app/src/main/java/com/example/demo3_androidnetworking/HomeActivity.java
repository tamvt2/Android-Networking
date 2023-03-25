package com.example.demo3_androidnetworking;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    ImageView img2;
    TextView tv2;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        img2 = findViewById(R.id.img_avata2);
        tv2 = findViewById(R.id.tv_email2);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String email = bundle.getString("email");
        String avata = bundle.getString("avatar");


        tv2.setText(email);
        Picasso.get().load(avata).into(img2);
    }
}
