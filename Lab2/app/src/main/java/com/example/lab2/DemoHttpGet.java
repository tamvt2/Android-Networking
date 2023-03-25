package com.example.lab2;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DemoHttpGet extends AsyncTask<String, Void, String> {
    TextView name;
    Context context;

    public DemoHttpGet(TextView name, MainActivity context) {
        this.name = name;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String noiDung = "";

        try {
            // Tạo ra đối tượng URL
            URL url = new URL( strings[0] );
            //2. Mở kết nối
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //3. Tạo đối tượng đọc luồng dữ liệu
            InputStream inputStream = conn.getInputStream();
            //4. Tạo bộ đệm đọc dữ liệu
            BufferedReader reader= new BufferedReader( new InputStreamReader( inputStream) );

            //5. Tạo biến ghép nối kết quả đọc
            StringBuilder builder = new StringBuilder();
            String dong;
            while (   (dong = reader.readLine())  != null     ){
                builder.append(dong)
                        .append("\n");
            }
            // kết thúc vòng lặp là đọc hết nội dung
            reader.close();
            inputStream.close();
            conn.disconnect();
            noiDung = builder.toString();  // hoàn thiện dữ liệu vào nội dung.
            Log.d("zzzzzz", "doInBackground: Kêt quả đọc:  " + noiDung);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return noiDung;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        name.setText(s);
        Log.e("TAG", "onPostExecute: "+s );
    }
}
