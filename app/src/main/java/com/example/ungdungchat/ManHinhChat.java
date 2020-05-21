package com.example.ungdungchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ManHinhChat extends AppCompatActivity implements ValueEventListener {
    ListView lvDanhSach;
    EditText edtMesage;
    Button btnGui;
    MesageAdapter mesageAdapter;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<User> dsChat=new ArrayList<User>();
    ArrayList<User> dsChat1=new ArrayList<User>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chat);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference().child("mesage");
        databaseReference.addValueEventListener(this);
        addControls();
        addEvents();
    }

    private void addEvents() {
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String emailDangNhap=intent.getStringExtra("Email");
                User user=new User(dsChat1.size(),emailDangNhap,edtMesage.getText().toString(),"visibli");
                dsChat1.add(user);
                databaseReference.setValue(dsChat1);
            }
        });
    }

    private void addControls() {
        lvDanhSach=(ListView)findViewById(R.id.lvDanhSach);
        edtMesage=(EditText)findViewById(R.id.edtMesage);
        btnGui=(Button)findViewById(R.id.btnGui);
        mesageAdapter=new MesageAdapter(ManHinhChat.this,R.layout.itemrow1);
        lvDanhSach.setAdapter(mesageAdapter);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ArrayList<User> ds1=new ArrayList<User>();
        Iterable<DataSnapshot>dataSnapshots=dataSnapshot.getChildren();
        for(DataSnapshot dataSnapshot1:dataSnapshots) {
            User user=dataSnapshot1.getValue(User.class);
            if(user.getHienThi().contains("visibli")) {
                ds1.add(user);
            }
        }
        if(mesageAdapter.getCount()>30){
            for(User user:dsChat1){
                user.setHienThi("hover");
                databaseReference.child(user.getViTri()+"").child("hienThi").setValue("hover");
            }
        }
        dsChat1.clear();
        mesageAdapter.clear();
        dsChat1.addAll(ds1);
        mesageAdapter.addAll(dsChat1);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
