package com.example.projectsem4.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.example.projectsem4.Entities.Employee;

public class EmployeeAdapter extends BaseAdapter {
    List<Employee> list;
    Context context;

    public EmployeeAdapter(List<Employee> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Employee getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        if (view == null){
//            LayoutInflater inflater =(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
//            view= inflater.inflate(R.layout.details,null);
//        }
//
//        TextView tvUsername = view.findViewById(R.id.tvUsername);
//        TextView tvPassword = view.findViewById(R.id.tvPassword);
//        TextView tvGender = view.findViewById(R.id.tvGender);
//        ImageView imgPhoto = view.findViewById(R.id.imgPhoto);
//
//        PhotoDTO photo = getItem(i);
//        tvUsername.setText(photo.getUsername());
//        tvPassword.setText(photo.getPassword());
//        tvGender.setText(String.valueOf(photo.getGender()));
//
//        String getImg = photo.getPhoto();
//        byte[] decodedString = Base64.decode(getImg, Base64.DEFAULT);
//        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        imgPhoto.setImageBitmap(decodedByte);
//
//
//        return view;
        return null;
    }
}
