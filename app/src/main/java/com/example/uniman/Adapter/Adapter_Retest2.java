package com.example.uniman.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Retest;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Retest2 extends RecyclerView.Adapter<Adapter_Retest2.ViewHolder_Retest2> {

    private Context context;
    private ArrayList<Retest> list;

    public Adapter_Retest2(Context context, ArrayList<Retest> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder_Retest2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_retest2,parent,false);
        return new ViewHolder_Retest2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Retest2 holder, int position) {
        Retest retest = list.get(position);
        holder.tv_tt.setText("");
        holder.tv_lhp.setText(retest.getLophp());
        holder.tv_tmh.setText(retest.getTenmh());
        holder.tv_tc.setText(""+retest.getTc());
        holder.tv_lp.setText(""+retest.getTienthi());
        holder.tv_ndk.setText(""+retest.getNgaydangky());
        //Log.e("lop",retest.getLophp());
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }


    public class ViewHolder_Retest2 extends RecyclerView.ViewHolder {
            private TextView tv_tt,tv_lhp,tv_tmh,tv_tc,tv_lp,tv_ndk;
        public ViewHolder_Retest2(@NonNull View v) {
            super(v);
            tv_tt = v.findViewById(R.id.tv_tt2);
            tv_lhp = v.findViewById(R.id.tv_lhp2);
            tv_tmh = v.findViewById(R.id.tv_tmh2);
            tv_tc = v.findViewById(R.id.tv_tc2);
            tv_lp = v.findViewById(R.id.tv_lp);
            tv_ndk = v.findViewById(R.id.tv_ndk);
        }
    }
}
