package com.example.uniman.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.uniman.Activity.LoginActivity;
import com.example.uniman.Adapter.Adapter_Notifi;
import com.example.uniman.Model.NotisenData;
import com.example.uniman.R;
import com.example.uniman.Retrofit.APINotifcation;
import com.example.uniman.Retrofit.RetrofitNotification;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Noti_ViewModel;
import com.example.uniman.ViewModel.NotifiViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;


public class NotificationFragment extends Fragment {

    private RecyclerView rcv_noti;
    private FloatingActionButton img_add;
    private NotifiViewModel notifiViewModel;
    //
    private AlertDialog alertDialog;
    private EditText ed_title,ed_body;
    private Button btn_add,btn_cancel;
    String title,body;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_notification, container, false);
        addcontroll(view);
        Loadlist();
        adevenst();
        return view;
    }

    private void addcontroll(View v) {
        rcv_noti = v.findViewById(R.id.rcv_noti);
        img_add = v.findViewById(R.id.img_add_noti);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_noti.setLayoutManager(linearLayoutManager);
        notifiViewModel = new ViewModelProvider(getActivity()).get(NotifiViewModel.class);
    }

    private void Loadlist() {

        notifiViewModel.getnotifi().observe(getActivity(),notifi_model -> {
            Adapter_Notifi adapter_notifi = new Adapter_Notifi(getContext(),notifi_model.getResult());
            rcv_noti.setAdapter(adapter_notifi);
        });
    }

    //
    private void adevenst() {
        if (Utils.user.getRole() == 1 || Utils.user.getRole() == 2){
           img_add.setVisibility(View.GONE);
        }else {
            img_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog();
                }
            });
        }

    }

    private void showDialog() {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_notifi, null);
        builder.setView(v);
        // hiển thị dialog
        alertDialog = builder.create();
        alertDialog.setCancelable(false);// ko cho user thoát
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
        alertDialog.show();
        //

        //

        addcontrolldialog(v);
        addevenstdialog();
    }
    private void addcontrolldialog(View v) {
        ed_body = v.findViewById(R.id.ed_body);
        ed_title = v.findViewById(R.id.ed_title);
        btn_cancel = v.findViewById(R.id.btn_cancel_noti);
        btn_add = v.findViewById(R.id.btn_add_noti);
    }

    private void addevenstdialog() {

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                body = ed_body.getText().toString().trim();
                title = ed_title.getText().toString().trim();
                punotifacation(title,body);
                alertDialog.dismiss();
            }
        });
    }

    private void themNotifi(String a, String b) {
        notifiViewModel.themnotifi(a,b).observe(getActivity(), notifi_model -> {
            if (notifi_model.isSuccess()) {
                Toast.makeText(getContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                Loadlist();
            }else {
                Log.e("themnotifi", "null");
            }
        });
    }

    private void punotifacation(String title, String body) {
       // if (Utils.user.getToken() != null && (Utils.user.getRole() == 1 || Utils.user.getRole() == 2)){
           Map<String,String> map = new HashMap<>();
            map.put("title",title);
            map.put("body",body);
            NotisenData notisenData = new NotisenData(Utils.user.getToken(), map);
            APINotifcation apiNotifcation = RetrofitNotification.getRetrofitNoti().create(APINotifcation.class);
            Noti_ViewModel noti_viewModel = new ViewModelProvider(getActivity()).get(Noti_ViewModel.class);
            noti_viewModel.data(notisenData).observe(getActivity(),notiResponse -> {
                // if (notiResponse.getSuccess() == 1)
            });
       // }


    }
}