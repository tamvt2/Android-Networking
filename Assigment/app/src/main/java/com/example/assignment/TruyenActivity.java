package com.example.assignment;

import static com.example.assignment.LoginActivity.IP;
import static com.example.assignment.LoginActivity.id;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.assignment.Adapter.Comment_Adapter;
import com.example.assignment.Model.Comment;
import com.example.assignment.Model.TruyenTranh;
import com.example.assignment.api.ApiLayComment;
import com.example.assignment.api.ApiLayUser;
import com.example.assignment.interfaces.LayCommentVe;
import com.squareup.picasso.Picasso;

public class TruyenActivity extends AppCompatActivity implements LayCommentVe {
    EditText comment;
    Button btn_comment, btn_doc;
    TruyenTranh truyenTranh = new TruyenTranh();
    ImageView img_nen, img;
    TextView tvname, tvmota;
    ArrayList<Comment> commentArrayList;
    Comment_Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);
        Bundle b = getIntent().getBundleExtra("data");
        truyenTranh = (TruyenTranh) b.getSerializable("truyen");
        comment = findViewById(R.id.edt_comment);
        img_nen = findViewById(R.id.img_anhnen);
        tvname = findViewById(R.id.tv_namecomic);
        tvmota = findViewById(R.id.tv_tieude);
        listView = findViewById(R.id.lv_comment);
        img = findViewById(R.id.img_back);
        commentArrayList = new ArrayList<>();
        btn_comment = findViewById(R.id.btn_comment);
        btn_doc = findViewById(R.id.btn_doc);
        Picasso.get().load("http://"+ IP +":3000/uploads/"+ truyenTranh.getAnh_bia()).into(img_nen);
        tvname.setText(truyenTranh.getTenTruyen());
        tvmota.setText(truyenTranh.getMoTa());
//        tvmota.setOnClickListener(view -> {
//            FrameLayout.LayoutParams textBoxLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
//                    FrameLayout.LayoutParams.WRAP_CONTENT);
//            menuContainer.setLayoutParams(textBoxLayoutParams);
//        });
        btn_comment.setOnClickListener(view -> {
            PostComment();
        });

        btn_doc.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), NoiDungActivity.class);
            intent.putExtra("data", truyenTranh.getId());
            startActivity(intent);
        });

        img.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        new ApiLayComment(this).execute("http://"+IP+":3000/api/comment/"+truyenTranh.getId());
    }

    public void PostComment(){
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy h:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            // tạo dữ liệu để gửi lên API
            JSONObject obj = new JSONObject();
            obj.put("id_story", truyenTranh.getId());
            obj.put("id_user", id);
            obj.put("content", comment.getText().toString().trim());
            obj.put("date", date);
            // tạo request body để gửi lên server
            final String reqBody = obj.toString();

            StringRequest req = new StringRequest(Request.Method.POST,
                    "http://"+ IP +":3000/api/comment",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Nơi dữ liệu nhận về
                            if (response.equals("true")) {
                                Toast.makeText(getApplicationContext(), "Bình luận thành công",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Bình luận thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }){
                // phần gửi đi
                @Override  // khai báo kiểu dữ liệu gửi lên API
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override // chuyển nội dung gửi thành mã byte
                public byte[] getBody() throws AuthFailureError {
                    if(reqBody == null)
                        return null ;
                    else
                        return reqBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override // bắt lỗi kết nối internet
                protected VolleyError parseNetworkError(VolleyError volleyError) {
                    return super.parseNetworkError(volleyError);
                }
            };

            // add req vào quee
            requestQueue.add(req );

        }catch (JSONException e){
            e.printStackTrace(); // ghi ra log cấu trúc lỗi
        }
    }

    @Override
    public void ketThuc(String data) {
        try {
            commentArrayList.clear();
            JSONArray arr = new JSONArray(data);
            for (int i=0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                commentArrayList.add(new Comment(obj));
            }
            adapter = new Comment_Adapter(this, 0 , commentArrayList);
            listView.setAdapter(adapter);
        } catch (JSONException e) {

        }
    }

    @Override
    public void loi() {
        Toast.makeText(this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
    }
}