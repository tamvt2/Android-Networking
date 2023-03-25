package com.example.demo3_androidnetworking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class dto_adapter extends ArrayAdapter<DTO> {
    private Context context;
    private ArrayList<DTO> arrayList;
    DTO dto;

    public dto_adapter(@NonNull Context context, int resource, ArrayList<DTO> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_list_view,null);
        }
        dto = this.arrayList.get(position);
        TextView tvname = convertView.findViewById(R.id.tv_name);
        TextView tvdes = convertView.findViewById(R.id.tv_des);
        ImageView imageView = convertView.findViewById(R.id.img_avata);

        tvname.setText(dto.getName());
        tvdes.setText(dto.getDescription());
        Picasso.get().load(dto.getImageURL()).into(imageView);

        return convertView;
    }
}
