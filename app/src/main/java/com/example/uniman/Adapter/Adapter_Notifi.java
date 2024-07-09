package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Notifi;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Notifi extends RecyclerView.Adapter<Adapter_Notifi.Notifi_ViewHolder> {

    private Context context;
    private ArrayList<Notifi> list;

    public Adapter_Notifi(Context context, ArrayList<Notifi> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Notifi_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notifi,parent,false);
        return new Notifi_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notifi_ViewHolder holder, int position) {
        Notifi notifi = list.get(position);
        holder.tv_title.setText(notifi.getTitle());
        holder.tv_body.setText(notifi.getBody());
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class Notifi_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title,tv_body;
        public Notifi_ViewHolder(@NonNull View v) {
            super(v);
            tv_body = v.findViewById(R.id.tv_body);
            tv_title = v.findViewById(R.id.tv_title_no);
        }
    }
}
