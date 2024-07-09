package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Model.Chat;
import com.example.uniman.Model.Student;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_Chat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Chat> list;
    private String send;
    private static final int TYPE_SEND= 1;
    private static final int TYPE_RECEIVE= 2;

    public Adapter_Chat(Context context, ArrayList<Chat> list, String send) {
        this.context = context;
        this.list = list;
        this.send = send;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.item_send_chat,parent,false);
            return new Send_ViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.item_received_chat,parent,false);
            return new Received_ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat chat = list.get(position);
        if (getItemViewType(position) == TYPE_SEND){
            ((Send_ViewHolder) holder).tv_sen.setText(chat.mess);
            ((Send_ViewHolder) holder).tv_sen_time.setText(chat.datetime);
        }else {
            ((Received_ViewHolder) holder).tv_receved_chat.setText(chat.mess);
            ((Received_ViewHolder) holder).tv_receved_time.setText(chat.datetime);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).send.equals(send)){// neu id nguoi gui voi id truyen vao trung nhau
            return TYPE_SEND;
        }else {
            return TYPE_RECEIVE;
        }
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    public class Send_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_sen,tv_sen_time;
        public Send_ViewHolder(@NonNull View v) {
            super(v);
            tv_sen = v.findViewById(R.id.tv_sen_chat);
            tv_sen_time = v.findViewById(R.id.tv_sen_time);
        }
    }
    public class Received_ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_receved_chat,tv_receved_time;
        public Received_ViewHolder(@NonNull View v) {
            super(v);
            tv_receved_chat = v.findViewById(R.id.tv_receved_chat);
            tv_receved_time = v.findViewById(R.id.tv_receved_time);
        }
    }
}
