package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uniman.Model.User;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Adapter_Adminchat extends RecyclerView.Adapter<Adapter_Adminchat.chatAdmin_ViewHolder> {
    public interface Onclick{
        void onclick(User user);
    }

    private Context context;
    private ArrayList<User> list;
    private Onclick onclick;

    public Adapter_Adminchat(Context context, ArrayList<User> list, Onclick onclick) {
        this.context = context;
        this.list = list;
        this.onclick = onclick;
    }

    @NonNull
    @Override
    public chatAdmin_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_adminchat,parent,false);
        return new chatAdmin_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull chatAdmin_ViewHolder holder, int position) {
        User user = list.get(position);
        if (user.getHinhanh() == null){
            Glide.with(context).load(R.drawable.th).into(holder.img_avata);
        }else {
            if (user.getHinhanh().contains("http")) {
                Glide.with(context).load(user.getHinhanh()).into(holder.img_avata);
            } else {
                String hinhanh = Utils.BASE + "images/" + (user.getHinhanh());
                Glide.with(context).load(hinhanh).into(holder.img_avata);
            }
        }

        holder.tv_name.setText(user.getName());
        holder.tv_me.setText(user.getChat());
        holder.layout_adminchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onclick(user);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null){
            return list.size();//
        }
        return 0;
    }



    public class chatAdmin_ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_avata;
        private TextView tv_name,tv_me;
        private LinearLayout layout_adminchat;
        public chatAdmin_ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_avata = itemView.findViewById(R.id.img_avata_chat);
            tv_name = itemView.findViewById(R.id.tv_name_chat);
            tv_me = itemView.findViewById(R.id.tv_me_chat);
            layout_adminchat= itemView.findViewById(R.id.layout_adminchat);
        }
    }
}
