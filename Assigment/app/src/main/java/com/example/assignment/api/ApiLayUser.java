package com.example.assignment.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.assignment.Adapter.Comment_Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiLayUser extends AsyncTask<String, Void, String> {
    Comment_Adapter context;
    String data;
    TextView user;

    public ApiLayUser(TextView user, Comment_Adapter context) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected String doInBackground(String... strings) {
        String noiDung = "";
        try {
            URL url = new URL( strings[0] );

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader= new BufferedReader( new InputStreamReader( inputStream) );

            StringBuilder builder = new StringBuilder();
            String dong;
            while (   (dong = reader.readLine())  != null     ){
                builder.append(dong)
                        .append("\n");
            }
            reader.close();
            inputStream.close();
            conn.disconnect();
            noiDung = builder.toString();

            try {
                JSONArray array = new JSONArray(noiDung);
                for (int i=0; i<array.length(); i++){
                    JSONObject obj = array.getJSONObject(i);
                    data = obj.getString("fullname");
                    Log.d("sdhcvb", "api: "+data);
                }

            } catch (JSONException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        if (data != null) {
            user.setText(data);
        }
    }
}
