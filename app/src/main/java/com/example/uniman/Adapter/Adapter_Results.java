package com.example.uniman.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uniman.Model.Results;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.google.android.material.tabs.TabLayout;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Results extends RecyclerView.Adapter<Adapter_Results.Viewholder_Results> {

    private Context context;
    private ArrayList<Results> list;

    public interface Onclickre {
        void oncick(Results results);
    }

    public Onclickre onclickre;

    public Adapter_Results(Context context, ArrayList<Results> list, Onclickre onclickre) {
        this.context = context;
        this.list = list;
        this.onclickre = onclickre;
    }

    public Adapter_Results(Context context, ArrayList<Results> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Viewholder_Results onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_resulst, parent, false);

        return new Viewholder_Results(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder_Results holder, int position) {
        Results resulst = list.get(position);
        String indexText = String.format("%02d", position + 1);
        holder.tv_stt.setText("" + indexText);


        if (Utils.user.getRole() == 2) {
            holder.tv_null.setVisibility(View.VISIBLE);
            holder.tv_hoten.setVisibility(View.VISIBLE);
            holder.tv_hoten.setText("" + resulst.getName());
        }

        holder.tv_tenmon.setText(resulst.getTenmh());
        holder.tv_stc.setText("" + resulst.getSotc());
        holder.tv_diemcc.setText("" + resulst.getDiemcc());
        holder.tv_dhs1.setText("" + resulst.getDiemhs1());
        holder.tv_dhs2.setText("" + resulst.getDiemhs2());
        holder.tv_dhs3.setText("" + resulst.getDiemhs3());
        holder.tv_diemthi1.setText("" + resulst.getDiemck1());
        if (resulst.getDiemck2() == 0) {
            holder.tv_diemthi2.setText("");
        } else {
            holder.tv_diemthi2.setText("" + resulst.getDiemck2());
        }
        //
        if (String.valueOf(resulst.getDiemck1()) != null && resulst.getDiemck2() == 0) {
            holder.tv_diemthi.setText("" + resulst.getDiemck1());
        } else if (resulst.getDiemck2() >= 0) {
            holder.tv_diemthi.setText("" + resulst.getDiemck2());
        }

        holder.tv_diemtk.setText("" + resulst.getTongdiem());

        holder.tv_td4.setText("" + resulst.getTd4());
        holder.tv_diemchu.setText(resulst.getDiemchu());
        holder.tv_xeploai.setText(resulst.getXeploai());
        holder.tv_ghichu.setText(resulst.getGhichu());

        holder.tableLayout.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(0, 0, getItemCount(), "Nhập Điểm").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Xử lý sự kiện khi người dùng chọn "Sửa"
                        // Ví dụ: mở màn hình chỉnh sửa thông tin sinh viên
                        onclickre.oncick(resulst);
                        return true;
                    }
                });
            }
        });


        if (String.valueOf(resulst.getImg()) != null) {
            if (resulst.getImg() == 1) {
                Glide.with(context).load(R.drawable.ok).into(holder.img);
            } else if (resulst.getImg() == 0) {
                Glide.with(context).load(R.drawable.xoa).into(holder.img);
            }
        } else {
            return;
        }
        //

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public class Viewholder_Results extends RecyclerView.ViewHolder {

        private TextView tv_stt, tv_tenmon, tv_stc, tv_diemcc, tv_dhs1, tv_dhs2, tv_hoten, tv_null, tv_ghichu,
                tv_dhs3, tv_diemthi1, tv_diemthi2, tv_diemthi, tv_diemtk, tv_td4, tv_diemchu, tv_xeploai;
        private ImageView img;
        private TableLayout tableLayout;

        public Viewholder_Results(@NonNull View v) {
            super(v);
            tv_stt = v.findViewById(R.id.tv_stt);
            tv_tenmon = v.findViewById(R.id.tv_tenmon);
            tv_stc = v.findViewById(R.id.tv_stc);
            tv_diemcc = v.findViewById(R.id.tv_diemcc);
            tv_dhs1 = v.findViewById(R.id.tv_dhs1);
            tv_dhs2 = v.findViewById(R.id.tv_dhs2);
            tv_dhs3 = v.findViewById(R.id.tv_dhs3);
            tv_diemthi1 = v.findViewById(R.id.tv_diemthi1);
            tv_diemthi2 = v.findViewById(R.id.tv_diemthi2);
            tv_diemthi = v.findViewById(R.id.tv_diemthi);
            tv_diemtk = v.findViewById(R.id.tv_diemtk);
            tv_td4 = v.findViewById(R.id.tv_td4);
            tv_diemchu = v.findViewById(R.id.tv_diemchu);
            tv_xeploai = v.findViewById(R.id.tv_xeploai);
            tv_ghichu = v.findViewById(R.id.tv_ghichu);
            img = v.findViewById(R.id.img);
            tv_hoten = v.findViewById(R.id.tv_hoten_item);
            tv_null = v.findViewById(R.id.t);
            tableLayout = v.findViewById(R.id.tablayout);


        }
    }
}
