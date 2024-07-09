package com.example.uniman.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.uniman.R;

public class ScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        ImageView img_unimain = findViewById(R.id.img_unimain);
        Glide.with(this).load(R.mipmap.unimain).into(img_unimain);
        // tự động chuyển màn hình
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ScreenActivity.this,LoginActivity.class);
                startActivity(intent);
                finishAffinity();// đóng activity
            }
        },1500); // thời gian chay
    }
}