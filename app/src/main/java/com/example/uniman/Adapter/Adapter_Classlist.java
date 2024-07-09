package com.example.uniman.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Listener.Listener_Classlist;
import com.example.uniman.Model.Class;
import com.example.uniman.Model.Majors;
import com.example.uniman.Model.Schedule;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;

import java.util.ArrayList;

public class Adapter_Classlist extends RecyclerView.Adapter<Adapter_Classlist.ClassList_ViewHolder> {
    // thêm mon học
//    public interface Addsubject {
//        void OnClickAddsubject(Class aClass);
//    }

    private Context context;
    private ArrayList<Class> list;
    private ArrayList<Schedule> listSchedule;
    private Listener_Classlist listener_classlist;// thêm

//    public Addsubject addsubject;
//
//
//    public Adapter_Classlist(Context context, ArrayList<Class> list, Addsubject addsubject) {
//        this.context = context;
//        this.list = list;
//        this.addsubject = addsubject;
//    }

    public Adapter_Classlist(Context context, ArrayList<Class> list, Listener_Classlist listener_classlist) {
        this.context = context;
        this.list = list;
        this.listener_classlist = listener_classlist;
    }

    @NonNull
    @Override
    public ClassList_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classlist, parent, false);
        return new ClassList_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassList_ViewHolder holder, int position) {
        Class aClass = list.get(position);
        holder.tv_class.setText(aClass.getTenlop());
        //int role = Utils.user.getRole();
        SharedPreferences sharedPreferences = context.getSharedPreferences("pc", Context.MODE_PRIVATE);
        int role = sharedPreferences.getInt("p", -1);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener_classlist.OnClickClass(aClass);
            }
        });

//        if (role == 1) {
//            holder.layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    addsubject.OnClickAddsubject(aClass);
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();// trả về số lượng item
        }
        return 0;
    }

    public class ClassList_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_class;
        private LinearLayout layout;

        public ClassList_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_class = itemView.findViewById(R.id.tv_classl);
            layout = itemView.findViewById(R.id.layout_class);
        }
    }
}
