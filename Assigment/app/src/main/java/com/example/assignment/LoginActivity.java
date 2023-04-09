package com.example.assignment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

public class LoginActivity extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_cancel, btn_login;
    TextView tv_dangky;
    public static String id = "";
    public static String IP = "192.168.1.6";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_login = findViewById(R.id.btn_login);
        btn_cancel = findViewById(R.id.btn_cancel);
        tv_dangky = findViewById(R.id.tv_dangky);

        btn_cancel.setOnClickListener(view -> {
            edt_username.setText("");
            edt_password.setText("");
        });

        tv_dangky.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });

        btn_login.setOnClickListener(view -> {
            if (Validate()) {
                DemoPostVolley();
            }
        });
    }

    public void DemoPostVolley(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        try {
            // tạo dữ liệu để gửi lên API
            JSONObject obj = new JSONObject();
            obj.put("Username", edt_username.getText().toString().trim());
            obj.put("Password", edt_password.getText().toString().trim());
//            obj.put("RepeatPassword","1231");
            // tạo request body để gửi lên server
            final String reqBody = obj.toString();

            StringRequest req = new StringRequest(Request.Method.POST,
                    "http://" + IP + ":3000/api/login",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Nơi dữ liệu nhận về
                            if (!response.equals("null")) {
                                id = response;
                                Toast.makeText(getApplicationContext(), "Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), "Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
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
//                    String response = "";
//                    if(volleyError != null)
//                        response =String.valueOf( volleyError.getMessage());

                    return super.parseNetworkError(volleyError);
                }
            };

            // add req vào quee
            requestQueue.add(req );

        }catch (JSONException e){
            e.printStackTrace(); // ghi ra log cấu trúc lỗi
        }
    }

    private boolean Validate() {
        String username = edt_username.getText().toString().trim();
        String password = edt_password.getText().toString().trim();
        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(getApplicationContext(),"Vui lòng không để trống thông tin",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}