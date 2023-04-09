package com.example.assignment.Adapter;

import static com.example.assignment.LoginActivity.IP;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment.Model.Content;
import com.example.assignment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Content_Adapter extends ArrayAdapter<Content>  {
    private Context context;
    private ArrayList<Content> arrayList;
    Content content;

    public Content_Adapter(@NonNull Context context, int resource, ArrayList<Content> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_content, null);
        }

        content =  this.arrayList.get(position);
        ImageView img = convertView.findViewById(R.id.img_content);
        Picasso.get().load("http://"+ IP +":3000/uploads/"+content.getUrl()).into(img);
        return convertView;
    }
}
