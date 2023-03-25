package com.example.demo3_androidnetworking;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class XuLyDangNhap extends AsyncTask<String, Void, String> {

    TextView tv_username;
    TextView tv_password;
    Context context;
    public String email;
    public String avata;

    ArrayList<User> arrayList = new ArrayList<>();

    public XuLyDangNhap(TextView tv_username, TextView tv_password, Context context) {
        this.tv_username = tv_username;
        this.tv_password = tv_password;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String thongtinlayve = "";
        try {

            URL url = new URL(strings[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            String dong;
            while ((dong = reader.readLine()) != null){
                builder.append(dong).append("\n");
            }

            reader.close();
            inputStream.close();
            connection.disconnect();
            thongtinlayve = builder.toString();

            try {
                JSONArray array = new JSONArray(thongtinlayve);
                for (int i =0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    arrayList.add(new User(object.getString("username"),object.getString("password"),object.getString("email"),object.getString("avatar")));

                    if (tv_username.getText().toString().trim().equals(arrayList.get(i).getUsername()) && tv_password.getText().toString().trim().equals(arrayList.get(i).getPassword())){
                        email = arrayList.get(i).getEmail();
                        avata = arrayList.get(i).getAvatar();

                        Log.e("tag","rrrrrrrrrrrrrr "+email +"-"+avata);
                        Intent intent = new Intent(context,HomeActivity.class);
                        Bundle b =new Bundle();
                        b.putSerializable("email",email);
                        b.putSerializable("avatar",avata);
                        intent.putExtras(b);
                        context.startActivity(intent);
                        break;
                    }
                }

            }catch (JSONException e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return thongtinlayve;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
