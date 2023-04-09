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

import com.example.assignment.Model.Comment;
import com.example.assignment.Model.User;
import com.example.assignment.R;
import com.example.assignment.api.ApiLayUser;

import java.util.ArrayList;

public class Comment_Adapter extends ArrayAdapter<Comment>  {
    private Context context;
    private ArrayList<Comment> arrayList;
    Comment commnet;

    public Comment_Adapter(@NonNull Context context, int resource, ArrayList<Comment> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_comment, null);
        }

        commnet =  this.arrayList.get(position);
        TextView binhluan = convertView.findViewById(R.id.tv_comment);
        TextView user = convertView.findViewById(R.id.tv_user);
        TextView gio = convertView.findViewById(R.id.tv_gio);
        binhluan.setText(commnet.getContent());
        new ApiLayUser(user, this).execute("http://"+ IP +":3000/api/user/"+ commnet.getId_user());
        gio.setText(commnet.getDate());
        return convertView;
    }
}
