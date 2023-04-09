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

import com.example.assignment.R;
import com.squareup.picasso.Picasso;
import com.example.assignment.Model.TruyenTranh;
import java.util.ArrayList;

public class Truyentranh_Adapter extends ArrayAdapter<TruyenTranh>  {
    private Context context;
    private ArrayList<TruyenTranh> arrayList;
    TruyenTranh truyenTranh;

    public Truyentranh_Adapter(@NonNull Context context, int resource, ArrayList<TruyenTranh> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
        this.truyenTranh = truyenTranh;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_truyentranh, null);
        }

        truyenTranh =  this.arrayList.get(position);
        TextView tentruyen = convertView.findViewById(R.id.tv_tentruyen);
        ImageView anh = convertView.findViewById(R.id.img_truyentranh);

        tentruyen.setText(truyenTranh.getTenTruyen());
        Picasso.get().load("http://"+ IP +":3000/uploads/"+truyenTranh.getAnh_bia()).into(anh);
        return convertView;
    }
}
