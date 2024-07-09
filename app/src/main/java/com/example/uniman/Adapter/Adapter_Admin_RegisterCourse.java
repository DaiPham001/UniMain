package com.example.uniman.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Semester;
import com.example.uniman.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Admin_RegisterCourse extends RecyclerView.Adapter<Adapter_Admin_RegisterCourse.ViewHolder_RegisterCourse> {

    public interface OnClick {
        void OnClick(Semester semester);
    }

    private Context context;
    private ArrayList<Semester> list;
    private OnClick onClick;

    public Adapter_Admin_RegisterCourse(Context context, ArrayList<Semester> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder_RegisterCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_admin_registercourse, parent, false);
        return new ViewHolder_RegisterCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_RegisterCourse holder, int position) {
        Semester semester = list.get(position);
        String indexText = String.format("%02d", position + 1);
        holder.tv_stt.setText(indexText);
        holder.tv_malophp.setText(semester.getMamh());
        holder.tv_tenmh.setText(semester.getTenmh());
        holder.tv_tinchi.setText(""+semester.getTinchi());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        String formattedHocphi = decimalFormat.format(semester.getHocphi());
        String displayText = formattedHocphi + "Đ";
        holder.tv_hp.setText(displayText);
        //Log.e("hp", String.valueOf(displayText));

        holder.tv_tengv.setText(""+semester.getTengv());
        holder.layout_rc.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 0, getItemCount(), "Sửa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onClick.OnClick(semester);
                        return true;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();// trả về sô luong item
        }
        return 0;
    }

    public class ViewHolder_RegisterCourse extends RecyclerView.ViewHolder {
        private TextView tv_stt,tv_malophp,tv_tenmh,tv_tengv,tv_tinchi,tv_hp;
        private TableLayout layout_rc;
        public ViewHolder_RegisterCourse(@NonNull View itemView) {
            super(itemView);
            tv_stt = itemView.findViewById(R.id.tv_stt_rg);
            tv_malophp=itemView.findViewById(R.id.tv_malophp);
            tv_tenmh=itemView.findViewById(R.id.tv_tenmh);
            tv_tinchi=itemView.findViewById(R.id.tv_tinchi);
            tv_hp=itemView.findViewById(R.id.tv_hp);
            tv_tengv =itemView.findViewById(R.id.tv_tengv);
            layout_rc = itemView.findViewById(R.id.layout_rc);
        }
    }
}
