package com.example.uniman.Adapter;

import android.content.Context;
import android.location.GnssAntennaInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Schedule;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_ClassGV extends RecyclerView.Adapter<Adapter_ClassGV.ClassGV_Viewholder> {

    public interface Onclick{
        void onclick(Schedule schedule);
    }

    private Context context;
    private ArrayList<Schedule> list;
    private Onclick onclick;


    public Adapter_ClassGV(Context context, ArrayList<Schedule> list,Onclick onclick) {
        this.context = context;
        this.list = list;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public ClassGV_Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classlist,parent,false);
        return new ClassGV_Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassGV_Viewholder holder, int position) {
        Schedule schedule = list.get(position);
        holder.tv_class.setText(schedule.getLop());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(schedule);
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

    public class ClassGV_Viewholder extends RecyclerView.ViewHolder {

        private TextView tv_class;
        private LinearLayout layout;
        public ClassGV_Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_class = itemView.findViewById(R.id.tv_classl);
            layout = itemView.findViewById(R.id.layout_class);
        }
    }
}
