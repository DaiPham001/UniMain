package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uniman.Model.Semester;
import com.example.uniman.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_User2_RegisterCourse extends RecyclerView.Adapter<Adapter_User2_RegisterCourse.ViewHolder_2RegisterCourse> {

    private Context context;
    private ArrayList<Semester> list;

    public Adapter_User2_RegisterCourse(Context context, ArrayList<Semester> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder_2RegisterCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user2_registercourse, parent, false);
        return new ViewHolder_2RegisterCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_2RegisterCourse holder, int position) {
        Semester semester = list.get(position);
        String indexText = String.format("%02d", position + 1);
        holder.tv_stt.setText(indexText);
        holder.tv_malophp.setText(semester.getMamh());
        holder.tv_tenmh.setText(semester.getTenmh());
        holder.tv_tinchi.setText("" + semester.getTinchi());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###.##");
        String formattedHocphi = decimalFormat.format(semester.getHocphi());
        String displayText = formattedHocphi + "Đ";
        holder.tv_hp.setText(displayText);


        holder.tv_lhdk.setText(semester.getLop());
        holder.tv_tongtc.setText(""+semester.getTongtc());
        holder.tv_ngaydk.setText(semester.getNgaydk());
        if (semester.getThu() == 1){
            Glide.with(context).load(R.drawable.ic_check_24).into(holder.img_trangthai);
        }else if (semester.getThu() == 0){
            Glide.with(context).load(R.drawable.xoa).into(holder.img_trangthai);
        }



    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();// trả về sô luong item
        }
        return 0;
    }

    public class ViewHolder_2RegisterCourse extends RecyclerView.ViewHolder {
        private TextView tv_stt, tv_malophp, tv_tenmh,tv_lhdk, tv_tinchi,tv_tongtc, tv_hp,tv_ngaydk;
        private ImageView img_trangthai;
        public ViewHolder_2RegisterCourse(@NonNull View itemView) {
            super(itemView);
            tv_stt = itemView.findViewById(R.id.tv_stt_u2_rg);
            tv_malophp = itemView.findViewById(R.id.tv_u2_malophp);
            tv_tenmh = itemView.findViewById(R.id.tv_u2_tenmh);
            tv_lhdk = itemView.findViewById(R.id.tv_lhdk);
            tv_tinchi = itemView.findViewById(R.id.tv_u2_tinchi);
            tv_tongtc = itemView.findViewById(R.id.tv_tongtc);
            tv_hp = itemView.findViewById(R.id.tv_u2_hp);
            img_trangthai = itemView.findViewById(R.id.img_trangthai);
            tv_ngaydk = itemView.findViewById(R.id.tv_ngaydk);
        }
    }
}
