package com.example.ungdungchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText edtTaiKhoan,edtMatKhau,edtMatKhau2;
    Button btnDangNhap,btnDangKi,btnTaiKhoan;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((edtTaiKhoan.getText().toString().equals(""))||(edtMatKhau.getText().toString().equals(""))||(edtMatKhau2.getText().toString().equals(""))) {
                    Toast.makeText(MainActivity.this, "Điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                }else {
                    //nếu mật khẩu lần 1,2 không  khớp hiển thị thông báo
                    if (edtMatKhau.getText().toString().equals(edtMatKhau2.getText().toString())) {
                        Toast.makeText(MainActivity.this, "Mật khẩu khớp!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Mật khẩu không khớp, nhập lại!", Toast.LENGTH_SHORT).show();
                    }
                    if (edtMatKhau.getText().toString().equals(edtMatKhau2.getText().toString())) {
                        firebaseAuth.createUserWithEmailAndPassword(edtTaiKhoan.getText().toString(), edtMatKhau.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            public void onComplete(Task<AuthResult> task) {
                                //nếu dăng nhập thành công
                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Tạo tài khoản thành công!", Toast.LENGTH_LONG).show();
                                }else{
                                    // đăng nhập thất bại tại vì email đã tồn tại
                                    Toast.makeText(MainActivity.this, "Email đã tồn tại! ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                }
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //nếu mật khẩu hoặc email chưa có hiển thị thông báo
                if (edtTaiKhoan.getText().toString().equals("") || edtMatKhau.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "Điền đầy đủ thông tin!", Toast.LENGTH_LONG).show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(edtTaiKhoan.getText().toString(), edtMatKhau.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete( Task<AuthResult> task) {
                            // nếu đăng nhập thành công
                            if (task.isSuccessful()) {
                                Intent intentTruyenEmail=new Intent(MainActivity.this,ManHinhChat.class);
                                intentTruyenEmail.putExtra("Email",edtTaiKhoan.getText().toString());
                                startActivity(intentTruyenEmail);
                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
            }
        });
        btnTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,TaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        edtTaiKhoan=(EditText)findViewById(R.id.edtTaiKhoan);
        edtMatKhau=(EditText)findViewById(R.id.edtMatKhau);
        edtMatKhau2=(EditText)findViewById(R.id.edtMatKhau2);
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        btnDangKi=(Button)findViewById(R.id.btnDangKi);
        btnTaiKhoan=(Button)findViewById(R.id.btnTaiKhoan);
    }
}
