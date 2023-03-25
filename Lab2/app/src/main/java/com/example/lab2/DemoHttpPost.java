package com.example.lab2;

import android.content.Context;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;

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

public class DemoHttpPost extends AsyncTask<String, Void, String> {

    String name ;
    String pass ;
    String email;
    Context context;

    public DemoHttpPost(String name, String pass, String email , Context context) {
        this.name = name;
        this.pass = pass;
        this.email = email;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {


        // Tạo ra đối tượng URL
        URL url = null;
        try {
            url = new URL( strings[0] );

            //1. Mở kết nối
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //2. Thiết lập phương thức POST
            conn.setRequestMethod("POST");
            // hãy tạo 1 resource trên mockapi.io để làm link nhận dữ liệu
            //3. Tạo đối tượng dữ liệu để gửi lên server.
            JSONObject postData = new JSONObject();
            postData.put( "username", name);
            postData.put("password", pass);
            postData.put("email", email);
            //4. thiết lập kiểu dữ liệu gửi lên server
            conn.setRequestProperty("Content-Type","application/json");
            //5. Tạo đối tượng output
            OutputStream outputStream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            //6. Ghi dữ liệu vào luồng output
            writer.append( postData.toString() );
            // xóa bộ đệm
            writer.flush();
            writer.close();
            outputStream.close();
            // không đóng connection ở đây , đọc dữ liệu server phản hồi
            //7 đọc
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader( inputStream ));
            StringBuilder builder = new StringBuilder();
            String dong;
            while ((dong = reader.readLine())!= null){
                builder.append(dong).append("\n");
            }
            reader.close(); inputStream.close(); conn.disconnect();
            Log.d("zzzzzzz", "doInBackground: Server phản hồi: " + builder.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
}
