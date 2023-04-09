package com.example.assignment.api;

import android.os.AsyncTask;
import android.util.Log;


import com.example.assignment.interfaces.LayCommentVe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiLayContent extends AsyncTask<String, Void, String> {
    String data;
    LayCommentVe layCommentVe;

    public ApiLayContent(LayCommentVe layCommentVe) {
        this.layCommentVe = layCommentVe;
    }

    @Override
    protected String doInBackground(String... strings) {
        String noiDung = "";

        try {
            // Tạo ra đối tượng URL
            URL url = new URL(strings[0]);
            //2. Mở kết nối
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //3. Tạo đối tượng đọc luồng dữ liệu
            InputStream inputStream = conn.getInputStream();
            //4. Tạo bộ đệm đọc dữ liệu
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            //5. Tạo biến ghép nối kết quả đọc
            StringBuilder builder = new StringBuilder();
            String dong;
            while ((dong = reader.readLine()) != null) {
                builder.append(dong)
                        .append("\n");
            }
            // kết thúc vòng lặp là đọc hết nội dung
            reader.close();
            inputStream.close();
            conn.disconnect();
            data = builder.toString();  // hoàn thiện dữ liệu vào nội dung.

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noiDung;
    }

    @Override
    protected void onPostExecute(String s) {
        if (data == null) {
            this.layCommentVe.loi();
        } else {
            this.layCommentVe.ketThuc(data);
        }
    }
}
