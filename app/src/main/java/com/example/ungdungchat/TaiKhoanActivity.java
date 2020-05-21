package com.example.ungdungchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class TaiKhoanActivity extends AppCompatActivity {
    EditText edtTaiKhoanDangNhap;
    Button btnThayDoiMatKhau;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        firebaseAuth=FirebaseAuth.getInstance();
        addControls();
        addEvents();
    }
    private void addEvents() {
        btnThayDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                firebaseAuth.sendPasswordResetEmail(edtTaiKhoanDangNhap.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(TaiKhoanActivity.this,"Kiểm Tra Email!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

    private void addControls() {
        edtTaiKhoanDangNhap=(EditText)findViewById(R.id.edtTaiKhoanDangNhap);
        btnThayDoiMatKhau=(Button)findViewById(R.id.btnThayDoiMatKhau);
    }
}
