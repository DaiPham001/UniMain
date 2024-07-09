package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Semester;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Yearcourese extends RecyclerView.Adapter<Adapter_Yearcourese.ViewHolder_Yearcourese> {
    public interface OnClick{
        void OnclickSemester(Semester semester);
    }

    private Context context;
    private ArrayList<Semester> list;
    private OnClick onClick;


    public Adapter_Yearcourese(Context context, ArrayList<Semester> list, OnClick onClick) {
        this.context = context;
        this.list = list;
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public ViewHolder_Yearcourese onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_yearcourse,parent,false);
        return new ViewHolder_Yearcourese(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder_Yearcourese holder, int position) {
        Semester semester = list.get(position);
        holder.tv_khoa.setText("Khóa "+semester.getKhoa());
        holder.layout_khoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.OnclickSemester(semester);
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

    public class ViewHolder_Yearcourese extends RecyclerView.ViewHolder {
        private TextView tv_khoa;
        private LinearLayout layout_khoa;
        public ViewHolder_Yearcourese(@NonNull View itemView) {
            super(itemView);
            tv_khoa = itemView.findViewById(R.id.tv_khoa);
            layout_khoa = itemView.findViewById(R.id.layout_khoa);
        }
    }
}
