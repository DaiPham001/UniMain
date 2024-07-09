package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Semester;
import com.example.uniman.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_User1_RegisterCourse extends RecyclerView.Adapter<Adapter_User1_RegisterCourse.ViewHolder_RegisterCourse> {

    public interface OnClick {
        void OnClick(Semester semester);
    }

    private Context context;
    private ArrayList<Semester> list;
    private OnClick onClick;

    public Adapter_User1_RegisterCourse(Context context, ArrayList<Semester> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder_RegisterCourse onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user1_registercourse, parent, false);
        return new ViewHolder_RegisterCourse(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_RegisterCourse holder, int position) {
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


//        holder.tv_lhdk.setText(semester.getLop());
//        holder.tv_tongtc.setText(""+semester.getTongtc());
//        holder.tv_ngaydk.setText(semester.getNgaydk());
//        if (semester.getThu() == 1){
//            Glide.with(context).load(R.drawable.ic_check_24).into(holder.img_trangthai);
//        }else if (semester.getThu() == 0){
//            Glide.with(context).load(R.drawable.xoa).into(holder.img_trangthai);
//        }
        //Log.e("hp", String.valueOf(displayText));

        holder.radio_rc.setChecked(semester.isSelected());
        holder.layout_ur_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Duyệt qua tất cả các ViewHolder và đặt trạng thái chọn của chúng thành false
                for (Semester item : list) {
                    item.setSelected(false);
                }
                // Đặt trạng thái chọn của ViewHolder hiện tại thành true
                semester.setSelected(true);

                // Cập nhật lại giao diện
                notifyDataSetChanged();

                // Gọi phương thức onClick_lt() với ViewHolder đã được chọn
                onClick.OnClick(semester);
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
        private TextView tv_stt, tv_malophp, tv_tenmh, tv_tinchi, tv_hp;
        private TableLayout layout_ur_rc;
        private RadioButton radio_rc;

        public ViewHolder_RegisterCourse(@NonNull View itemView) {
            super(itemView);
            tv_stt = itemView.findViewById(R.id.tv_stt_u_rg);
            tv_malophp = itemView.findViewById(R.id.tv_u_malophp);
            radio_rc = itemView.findViewById(R.id.radio_rc);
            tv_tenmh = itemView.findViewById(R.id.tv_u_tenmh);
            tv_tinchi = itemView.findViewById(R.id.tv_u_tinchi);
            tv_hp = itemView.findViewById(R.id.tv_u_hp);
            layout_ur_rc = itemView.findViewById(R.id.layout_ur_rc);
        }
    }
}
