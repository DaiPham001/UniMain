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
import com.example.uniman.Model.Majors;
import com.example.uniman.R;

import java.util.ArrayList;

public class AdapterSpinner_Class extends ArrayAdapter<Class> {
    private Context context;
    private ArrayList<Class> list;
    private TextView  tv_chganh;

    public AdapterSpinner_Class(@NonNull Context context, ArrayList<Class> list) {
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
            view = inflater.inflate(R.layout.cnganh_item_spinner, null);
        }
        final Class aClass = list.get(position);
        if (view != null) {
            tv_chganh = view.findViewById(R.id.tv_chganh);
            int a = 0;
            for (int i = 0; i < list.size(); i++) {
                a++;

            }

            tv_chganh.setText(aClass.getTenlop());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cnganh_item_spinner, null);
        }
        final Class aClass = list.get(position);
        if (view != null) {
            tv_chganh = view.findViewById(R.id.tv_chganh);
            int a = 0;
            for (int i = 0; i < list.size(); i++) {
                a++;
                Log.e("a", String.valueOf(a));
                //tv_mas.setText(a + "-");
            }


            tv_chganh.setText(aClass.getTenlop());
        }
        return view;
    }
}
