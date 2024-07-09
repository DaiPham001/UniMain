package com.example.uniman.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.uniman.R;

import java.util.ArrayList;

public class Adapter_pager extends PagerAdapter {
    private Context context;
    private ArrayList<String> list;

    public Adapter_pager(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size(); // trả về số lượng item
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_page, container, false);
        // ánh xạ view
        ImageView img_pager = view.findViewById(R.id.img_page);
        String imageUrl = list.get(position);
        if (list != null){
            Glide.with(context).load(imageUrl).into(img_pager);
        }
        // add view to viewgroup
        container.addView(view);
        return view;
    }
}
