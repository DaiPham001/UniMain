package com.example.uniman.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uniman.Model.Class;
import com.example.uniman.Model.Teacher;
import com.example.uniman.R;

import java.util.ArrayList;

public class AdapterSpinner_tengv extends ArrayAdapter<Teacher> {
    private Context context;
    private ArrayList<Teacher> list;
    private TextView  tv_tengv,tv_magv;

    public AdapterSpinner_tengv(@NonNull Context context, ArrayList<Teacher> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tengv_item_spinner, null);
        }
        final Teacher teacher = list.get(position);
        if (view != null) {
            tv_tengv = view.findViewById(R.id.tv_tengiangvien);
            tv_magv = view.findViewById(R.id.tv_magv);
            String indexText = String.format("%2d", position + 1);
            tv_magv.setText(indexText + " - ");
            tv_tengv.setText(teacher.getTengv());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.tengv_item_spinner, null);
        }
        final Teacher teacher = list.get(position);
        if (view != null) {
            tv_tengv = view.findViewById(R.id.tv_tengiangvien);
            tv_magv = view.findViewById(R.id.tv_magv);
            String indexText = String.format("%2d", position + 1);
            tv_magv.setText(indexText + " - ");
            tv_tengv.setText(teacher.getTengv());
        }
        return view;
    }
}
