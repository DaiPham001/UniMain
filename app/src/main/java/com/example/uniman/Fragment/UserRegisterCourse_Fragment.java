package com.example.uniman.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniman.Adapter.AdapterSpinner_RegisterCourse;
import com.example.uniman.Adapter.Adapter_User1_RegisterCourse;
import com.example.uniman.Adapter.Adapter_User2_RegisterCourse;
import com.example.uniman.Model.Semester;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.RegisterCourse_ViewModel;
import com.example.uniman.ViewModel.User_RegisterCourse_ViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class UserRegisterCourse_Fragment extends Fragment implements Adapter_User1_RegisterCourse.OnClick {

    private RegisterCourse_ViewModel registerCourse_viewModel;
    private User_RegisterCourse_ViewModel user_registerCourse_viewModel;
    private Spinner spinner_dk;
    private Button btn_dk_rc;
    private RecyclerView rcv_chuadk, rcv_dadk;
    private TextView tv_null;
    Calendar calendar = Calendar.getInstance();
    // format cách hiển thị ngày/tháng/năm
    SimpleDateFormat sdfD = new SimpleDateFormat("dd/MM/yyyy");

    String manganh, namhoc, hocki, mamh, tenmh, date;
    int mgv, tinchi, khoa, trangthai = 0;
    double hocphi;
    boolean check;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_user_register_course_, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addcontroll(View v) {
        rcv_chuadk = v.findViewById(R.id.rcv_chuadk);
        rcv_dadk = v.findViewById(R.id.rcv_dadk);
        spinner_dk = v.findViewById(R.id.spinner_dk);
        btn_dk_rc = v.findViewById(R.id.btn_dk_rc);
        tv_null = v.findViewById(R.id.tv_null_rc);
        user_registerCourse_viewModel = new ViewModelProvider(getActivity()).get(User_RegisterCourse_ViewModel.class);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcv_chuadk.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        rcv_dadk.setLayoutManager(linearLayoutManager2);
        //

        Namhoc();

    }

    //
    private void addevenst() {
        spinner_dk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Semester semester = (Semester) parent.getItemAtPosition(position);
//                namhoc = semester.getNamhoc();
//                hocki = semester.getHocki();
                LoadListchuadangky(semester.getNamhoc(), semester.getHocki());
                LoadListdadangky(semester.getNamhoc(), semester.getHocki());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ///
//        if (check == false){
//            Toast.makeText(getContext(), "Chọn môn", Toast.LENGTH_SHORT).show();
//        }else {
        btn_dk_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trangthai = 1;
                date = sdfD.format(calendar.getTime());
                // Log.e("ma",String.valueOf(Utils.user.getMa()));
                user_registerCourse_viewModel.themmonuser(Utils.user.getMa(), khoa, manganh, namhoc, hocki, mamh, tenmh, Utils.user.getLop(), tinchi, 0, hocphi, trangthai, date).observe(getActivity(), semester_model -> {
                    if (semester_model.isSuccess()) {
                        LoadListdadangky(namhoc, hocki);
                        Log.e("f", namhoc + hocki);
                    } else {
                        Log.e("null", "null1");
                    }
                });
            }
        });
        //}


    }

    private void Namhoc() {
        //Log.e("tennganh",tennganh);
        registerCourse_viewModel = new ViewModelProvider(getActivity()).get(RegisterCourse_ViewModel.class);
        registerCourse_viewModel.getnamhoc(Utils.user.getKhoa()).observe(getActivity(), semester_model -> {
            if (semester_model.isSuccess()) {
                AdapterSpinner_RegisterCourse adapterSpinner_registerCourse = new AdapterSpinner_RegisterCourse(getContext(), semester_model.getResult());
                spinner_dk.setAdapter(adapterSpinner_registerCourse);
            } else {
                Log.e("null", "null2");
            }

        });
    }

    private void LoadListchuadangky(String a, String b) {
        user_registerCourse_viewModel.getmonchuadangky(Utils.user.getKhoa(), a, b).observe(getActivity(), semester_model -> {
            if (semester_model.isSuccess() && semester_model.getResult() != null && !semester_model.getResult().isEmpty()) {
                rcv_chuadk.setVisibility(View.VISIBLE);
                Adapter_User1_RegisterCourse adapter_user1_registerCourse = new Adapter_User1_RegisterCourse(getContext(), semester_model.getResult(), this);
                rcv_chuadk.setAdapter(adapter_user1_registerCourse);
                //Log.e("f", a + b + Utils.user.getKhoa());
                // tv_null.setVisibility(View.GONE);
            } else {
                rcv_chuadk.setVisibility(View.GONE);
                tv_null.setVisibility(View.VISIBLE);
            }
        });
    }

    private void LoadListdadangky(String a, String b) {
        user_registerCourse_viewModel.getmondadangky(Utils.user.getMa(), khoa, a, b).observe(getActivity(), semester_model -> {
            if (semester_model.isSuccess() && semester_model.getResult() != null && !semester_model.getResult().isEmpty()) {
                //rcv_chuadk.setVisibility(View.VISIBLE);
                Adapter_User2_RegisterCourse adapter_user2_registerCourse = new Adapter_User2_RegisterCourse(getContext(), semester_model.getResult());
                rcv_dadk.setAdapter(adapter_user2_registerCourse);

                // tv_null.setVisibility(View.GONE);
            } else {
//                rcv_chuadk.setVisibility(View.GONE);
//                tv_null.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void OnClick(Semester semester) {
        check = semester.isSelected();
        khoa = semester.getKhoa();
        manganh = semester.getManganh();
        namhoc = semester.getNamhoc();
        hocki = semester.getHocki();
        mamh = semester.getMamh();
        tenmh = semester.getTenmh();
        mgv = semester.getMgv();
        tinchi = semester.getTinchi();
        hocphi = semester.getHocphi();

    }
}