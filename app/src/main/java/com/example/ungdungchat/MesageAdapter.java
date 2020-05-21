package com.example.ungdungchat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MesageAdapter extends ArrayAdapter<User> {
    Activity context;
    int resource;
    public MesageAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View customView=this.context.getLayoutInflater().inflate(this.resource,null);

        TextView txtEmail=(TextView)customView.findViewById(R.id.txtEmail);
        TextView txtMesage=(TextView)customView.findViewById(R.id.txtMesage);

        User user=getItem(position);
        txtEmail.setText(user.getEmail());
        txtMesage.setText(user.getMesage());
        Animation animation= AnimationUtils.loadAnimation(this.context,R.anim.moroisang);
        customView.startAnimation(animation);
        return customView;
    }
}
