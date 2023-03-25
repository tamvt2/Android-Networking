package com.example.demo3_androidnetworking;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Post_Http extends AsyncTask<String, Void, String> {

    String name;
    String pass;
    String email;
    String avatar;
    Context context;

    public Post_Http(String name, String pass, String email,String avatar, Context context) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.context = context;
        this.avatar = avatar;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("username",name);
            jsonObject.put("password",pass);
            jsonObject.put("email",email);
            jsonObject.put("avatar",avatar);

            connection.setRequestProperty("Content-Type","application/json");
            OutputStream outputStream =connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            writer.append(jsonObject.toString());
            writer.flush();
            writer.close();
            outputStream.close();

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

        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
