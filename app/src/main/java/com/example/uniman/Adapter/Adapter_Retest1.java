package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Retest;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Retest1 extends RecyclerView.Adapter<Adapter_Retest1.ViewHolder_Retest1> {

    private Context context;
    private ArrayList<Retest> list;

    public interface OnClick{
        void OnClick_lt(Retest retest);
    }

    public OnClick onClick;

    public Adapter_Retest1(Context context, ArrayList<Retest> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder_Retest1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_retest1,parent,false);
        return new ViewHolder_Retest1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Retest1 holder, int position) {
        Retest retest = list.get(position);

        holder.tv_tt.setText("");
        holder.tv_mmh.setText(""+retest.getMamh());
        holder.tv_lhp.setText(""+retest.getLophp());
        holder.tv_tmh.setText(""+retest.getTenmh());
        holder.tv_tc.setText(""+retest.getTc());
        holder.tv_kq1.setText(""+retest.getDiemck1());
        holder.tv_gc.setText("");
        // Xác định xem ViewHolder này có đang được chọn không
        holder.radio.setChecked(retest.isSelected());

        holder.layout_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Duyệt qua tất cả các ViewHolder và đặt trạng thái chọn của chúng thành false
                for (Retest item : list) {
                    item.setSelected(false);
                }
                // Đặt trạng thái chọn của ViewHolder hiện tại thành true
                retest.setSelected(true);

                // Cập nhật lại giao diện
                notifyDataSetChanged();

                // Gọi phương thức onClick_lt() với ViewHolder đã được chọn
                onClick.OnClick_lt(retest);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }


    public class ViewHolder_Retest1 extends RecyclerView.ViewHolder {
        private RadioButton radio;
        private TextView tv_tt, tv_mmh, tv_lhp, tv_tmh, tv_tc, tv_kq1, tv_gc;
        private LinearLayout layout_re;
        public ViewHolder_Retest1(@NonNull View v) {
            super(v);
            radio = v.findViewById(R.id.radio);
            tv_tt = v.findViewById(R.id.tv_tt);
            tv_mmh = v.findViewById(R.id.tv_mmh);
            tv_lhp = v.findViewById(R.id.tv_lhp);
            tv_tmh = v.findViewById(R.id.tv_tmh);
            tv_tc = v.findViewById(R.id.tv_tc);
            tv_kq1 = v.findViewById(R.id.tv_kq1);
            tv_gc = v.findViewById(R.id.tv_gc);
            layout_re = v.findViewById(R.id.layout_re);
        }
    }
}
