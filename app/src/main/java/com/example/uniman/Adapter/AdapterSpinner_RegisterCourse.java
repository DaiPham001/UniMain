package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.uniman.Model.Semester;
import com.example.uniman.R;

import java.util.ArrayList;

public class AdapterSpinner_RegisterCourse extends ArrayAdapter<Semester> {
    private Context context;
    private ArrayList<Semester> list;
    private TextView  tv_namhoc,tv_hocki;

    public AdapterSpinner_RegisterCourse(@NonNull Context context, ArrayList<Semester> list) {
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
            view = inflater.inflate(R.layout.hocki_item_spinner, null);
        }
        final Semester Semester = list.get(position);
        if (view != null) {

            tv_namhoc = view.findViewById(R.id.tv_namhoc);
            tv_hocki = view.findViewById(R.id.tv_hocki);
            tv_namhoc.setText("("+Semester.getNamhoc()+") ");
            tv_hocki.setText(Semester.getHocki());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.hocki_item_spinner, null);
        }
        final Semester Semester = list.get(position);
        if (view != null) {

            tv_namhoc = view.findViewById(R.id.tv_namhoc);
            tv_hocki = view.findViewById(R.id.tv_hocki);
            tv_namhoc.setText("("+Semester.getNamhoc()+") ");
            tv_hocki.setText(Semester.getHocki());
        }
        return view;
    }
}
