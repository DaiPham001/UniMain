package com.example.uniman.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Listener.Listener_Majors;
import com.example.uniman.Model.Majors;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Industry extends RecyclerView.Adapter<Adapter_Industry.Industry_ViewHolder> {
    private Context context;
    private ArrayList<Majors> list;
    private Listener_Majors listener_majors;

    public Adapter_Industry(Context context, ArrayList<Majors> list, Listener_Majors listener_majors) {
        this.context = context;
        this.list = list;
        this.listener_majors = listener_majors;
    }

    @NonNull
    @Override
    public Industry_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_industry,parent,false);
        return new Industry_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Industry_ViewHolder holder, int position) {
        Majors majors = list.get(position);
        holder.tv_tennganh.setText(majors.getTennganh());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener_majors.OnClickMajors(majors);
            }
        });
        holder.layout.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0,0,getItemCount(),"Sửa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem item) {
                        listener_majors.OnClickUpdate(majors);
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size(); // trả về số lượng item
        }
        return 0;
    }

    public class Industry_ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tennganh;
        private LinearLayout layout;
        public Industry_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tennganh = itemView.findViewById(R.id.tv_nameng);
            layout = itemView.findViewById(R.id.layout_mj);
        }
    }
}
