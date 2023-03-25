package com.example.demo3_androidnetworking;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Get_Http extends AsyncTask<String, Void, String> {

    Context context;
    dto_adapter adapter;
    ListView listView;
    ArrayList<DTO> arrayList = new ArrayList<>();

    public Get_Http(ListView listView, Context context) {
        this.context = context;
        this.listView = listView;
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
                for (int i =0;i<array.length();i++){
                    JSONObject object = array.getJSONObject(i);
                    arrayList.add(new DTO(object.getString("name"), object.getString("description"), object.getString("image")));
                }
                adapter = new dto_adapter(context,0,arrayList);

            }catch (JSONException e){
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        return noiDung;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        listView.setAdapter(adapter);
    }
}
