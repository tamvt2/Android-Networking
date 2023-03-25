package com.example.assigment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

public class RegisterActivity extends AppCompatActivity {
    EditText edt_username, edt_password, edt_Repassword;
    Button btn_dangky, btn_cancel;
    TextView tv_dangnhap;
    String url_api = "http://192.168.0.104:3000/api/register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edt_username = findViewById(R.id.edt_dk_username);
        edt_password = findViewById(R.id.edt_dk_password);
        edt_Repassword = findViewById(R.id.edt_dk_Repassword);
        btn_cancel = findViewById(R.id.btn_dk_cancel);
        btn_dangky = findViewById(R.id.btn_dk_dangky);
        tv_dangnhap = findViewById(R.id.tv_dangnhap);

        btn_cancel.setOnClickListener(view -> {
            edt_username.setText("");
            edt_password.setText("");
        });

        tv_dangnhap.setOnClickListener(view -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });

        btn_dangky.setOnClickListener(view -> {
            if (Validate()) {
                DemoPostVolley();
            }
        });
    }

    public void DemoPostVolley() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            // tạo dữ liệu để gửi lên API
            JSONObject obj = new JSONObject();
            obj.put("Username", edt_username.getText().toString().trim());
            obj.put("Password", edt_password.getText().toString().trim());
            // tạo request body để gửi lên server
            final String reqBody = obj.toString();

            StringRequest req = new StringRequest(Request.Method.POST,
                    url_api,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Nơi dữ liệu nhận về
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            }) {
                // phần gửi đi
                @Override  // khai báo kiểu dữ liệu gửi lên API
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override // chuyển nội dung gửi thành mã byte
                public byte[] getBody() throws AuthFailureError {
                    if (reqBody == null)
                        return null;
                    else
                        return reqBody.getBytes(StandardCharsets.UTF_8);
                }

                @Override // bắt lỗi kết nối internet
                protected VolleyError parseNetworkError(VolleyError volleyError) {
//                    String response = "";
//                    if(volleyError != null)
//                        response =String.valueOf( volleyError.getMessage());

                    return super.parseNetworkError(volleyError);
                }
            };

            // add req vào quee
            requestQueue.add(req);

        } catch (JSONException e) {
            e.printStackTrace(); // ghi ra log cấu trúc lỗi
        }
    }

    private boolean Validate() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        String repassword = edt_Repassword.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui lòng không để trống thông tin",Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.length()<5){
            Toast.makeText(getApplicationContext(),"Mật khẩu phải nhiều hơn 5 ký tự",Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(repassword)) {
            Toast.makeText(getApplicationContext(), "Nhập lại mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}