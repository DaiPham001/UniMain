package com.example.uniman.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Schedule;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;

import java.util.ArrayList;

public class Schedule_Adapter extends RecyclerView.Adapter<Schedule_Adapter.ViewHolder_Schedule> {
    public interface OnClick_Schedule{
        void onclickSchedule(Schedule schedule);
        void oncpauseSchedule(Schedule schedule);
    }

    private Context context;
    private ArrayList<Schedule> list;
    private OnClick_Schedule onClick_schedule;


    public Schedule_Adapter(Context context, ArrayList<Schedule> list,OnClick_Schedule onClick_schedule) {
        this.context = context;
        this.list = list;
        this.onClick_schedule = onClick_schedule;
    }
    public Schedule_Adapter(Context context, ArrayList<Schedule> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder_Schedule onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.iteam_schedule,parent,false);
        return new ViewHolder_Schedule(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Schedule holder, int position) {
        Schedule schudeule = list.get(position);
        holder.tv_monh.setText("Môn hoc: "+schudeule.getTenmh());
        holder.tv_mamh.setText("MãMH: "+schudeule.getMamh());
        holder.tv_tiet.setText("Tiết: "+schudeule.getTiet());
        holder.tv_room.setText("Phòng: "+schudeule.getRoom());
        holder.tv_gv.setText("GV: "+schudeule.getTengv());
        holder.tv_gio.setText("Giờ: "+schudeule.getGio());
        //
        holder.layout.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                if (Utils.user.getRole() == 2){
                    menu.add(0,0,getItemCount(),"Sửa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(@NonNull MenuItem item) {
                                onClick_schedule.onclickSchedule(schudeule);
                            return true;
                        }
                    });

                    menu.add(0,1,getItemCount(),"Tạm Ngưng").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(@NonNull MenuItem item) {
                            onClick_schedule.oncpauseSchedule(schudeule);
                            return true;
                        }
                    });
                }else {
                    return;
                }

            }
        });
        if (schudeule.getTrangthai() ==0 ){
            holder.tv_tt.setVisibility(View.VISIBLE);
            int color = context.getResources().getColor(R.color.red); // Lấy màu từ tài nguyên màu
            holder.layout.setBackgroundColor(color);
        }else if (schudeule.getTrangthai() ==1){
            return;
        }else if (schudeule.getTrangthai() ==2){

        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size(); // trả về số lương item
        }
        return 0;
    }

    public class ViewHolder_Schedule extends RecyclerView.ViewHolder {
        private TextView tv_monh, tv_mamh, tv_tiet, tv_gio, tv_room, tv_gv,tv_tt;
        private LinearLayout layout;
        public ViewHolder_Schedule(@NonNull View v) {
            super(v);
            tv_monh = v.findViewById(R.id.tv_monh);
            tv_mamh = v.findViewById(R.id.tv_mamh);
            tv_tiet = v.findViewById(R.id.tv_tiet);
            tv_gio = v.findViewById(R.id.tv_gio);
            tv_room = v.findViewById(R.id.tv_room);
            tv_gv = v.findViewById(R.id.tv_gv);
            tv_tt = v.findViewById(R.id.tv_tt);
            layout = v.findViewById(R.id.layout_sd);
        }
    }
}
