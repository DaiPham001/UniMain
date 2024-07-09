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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniman.Adapter.AdapterSpinner_RegisterCourse;
import com.example.uniman.Adapter.AdapterSpinner_tengv;
import com.example.uniman.Adapter.Adapter_Admin_RegisterCourse;
import com.example.uniman.Model.Semester;
import com.example.uniman.Model.Teacher;
import com.example.uniman.R;
import com.example.uniman.ViewModel.RegisterCourse_ViewModel;
import com.example.uniman.ViewModel.Teacher_ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class AdminRegisterCourse_Fragment extends Fragment implements Adapter_Admin_RegisterCourse.OnClick {

    private Spinner sp_namhoc;
    private ArrayList<Semester> list_namhoc;
    private TextView tv_null,tv_hp;
    private RecyclerView rcv;
    private FloatingActionButton float_add_dk;
    int khoa, tinchi;
    String tennganh, manganh;
    RegisterCourse_ViewModel registerCourse_viewModel;
    // dialog
    private AlertDialog alertDialog;
    private Button btn_nhap, btn_huy;
    private TextView tv_title, tv_khoa;
    private Spinner sp_namhoc_add, sp_tinchi,sp_tengv;
    private EditText ed_mamh, ed_monh;
    private  ArrayList<Integer> list;
    String namhoc, hocki;
    boolean tem;
    String mamh, tenmh, tengv;
    double hp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_admin_register_course_, container, false);
        addcontroll(v);
        addevenst();
        return v;
    }

    private void addcontroll(View v) {

        tv_null = v.findViewById(R.id.tv_nullll);
        sp_namhoc = v.findViewById(R.id.spinner_dkgv);
        rcv = v.findViewById(R.id.rcv_dkgv);
        float_add_dk = v.findViewById(R.id.float_add_dk);
        registerCourse_viewModel = new ViewModelProvider(getActivity()).get(RegisterCourse_ViewModel.class);
        Namhoc();
    }

    private void Namhoc() {
        khoa = getArguments().getInt("khoa");
        tennganh = getArguments().getString("tennganh");
        manganh = getArguments().getString("manganh");
        //Log.e("tennganh",tennganh);
        registerCourse_viewModel.getnamhoc(khoa).observe(getActivity(), semester_model -> {
            AdapterSpinner_RegisterCourse adapterSpinner_registerCourse = new AdapterSpinner_RegisterCourse(getContext(), semester_model.getResult());
            if (semester_model.isSuccess()){
                sp_namhoc.setAdapter(adapterSpinner_registerCourse);
                list_namhoc = new ArrayList<>();
                list_namhoc = semester_model.getResult();
            }else {
                Log.e("null","null");
            }

        });
    }

    private void addevenst() {
        float_add_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tem = true;
                OpenDialog();
            }
        });

        sp_namhoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Semester semester = (Semester) parent.getItemAtPosition(position);

                LoadList(semester.getNamhoc(), semester.getHocki());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    private void LoadList(String namhoc, String hocki) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv.setLayoutManager(linearLayoutManager);

        registerCourse_viewModel.getsemesteradmin(khoa, namhoc, hocki).observe(getActivity(), semester_model -> {
            if (semester_model.isSuccess() && semester_model.getResult() != null && !semester_model.getResult().isEmpty()) {
                // Nếu dữ liệu không rỗng, hiển thị rcv và cập nhật dữ liệu
                rcv.setVisibility(View.VISIBLE);
                tv_null.setVisibility(View.GONE);
                Adapter_Admin_RegisterCourse adapter_registerCourse = new Adapter_Admin_RegisterCourse(getContext(), semester_model.getResult(), this);
                rcv.setAdapter(adapter_registerCourse);
                Log.e("f",namhoc+hocki);
            } else {
                // Nếu dữ liệu rỗng, ẩn rcv
                rcv.setVisibility(View.GONE);
                tv_null.setVisibility(View.VISIBLE);
                // Có thể hiển thị thông báo hoặc thực hiện các hành động khác tùy ý
                Log.e("NoData", "Không có dữ liệu");
            }
        });
    }

    public void OpenDialog() {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_registercourse, null);
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
        btn_huy = v.findViewById(R.id.btn_huy_add);
        btn_nhap = v.findViewById(R.id.btn_nhap_add);
        tv_title = v.findViewById(R.id.tv_titl_tenganh);
        tv_khoa = v.findViewById(R.id.tv_khoa_add);
        sp_namhoc_add = v.findViewById(R.id.sp_namhoc_hocki);
        sp_tinchi = v.findViewById(R.id.sp_tinchi);
        ed_mamh = v.findViewById(R.id.ed_mamh);
        ed_monh = v.findViewById(R.id.ed_monh);
        sp_tengv = v.findViewById(R.id.spiner_tengv);
        tv_hp = v.findViewById(R.id.tv_hp_add);

        //
        tv_title.setText("Ngành: " + tennganh);
        tv_khoa.setText("Khóa: " + khoa);


        registerCourse_viewModel.getnamhoc(khoa).observe(getActivity(), semester_model -> {
            AdapterSpinner_RegisterCourse adapterSpinner_registerCourse = new AdapterSpinner_RegisterCourse(getContext(), semester_model.getResult());
            sp_namhoc_add.setAdapter(adapterSpinner_registerCourse);
        });

        list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list);
        sp_tinchi.setAdapter(adapter);

        // spinner ten giang vien
        Teacher_ViewModel teacher_viewModel = new  ViewModelProvider(getActivity()).get(Teacher_ViewModel.class);
        teacher_viewModel.gettengv(manganh).observe(getActivity(), teacher_model -> {
            AdapterSpinner_tengv adapterSpinner_tengv = new AdapterSpinner_tengv(getContext(),teacher_model.getResult());
            sp_tengv.setAdapter(adapterSpinner_tengv);
        });
    }

    private void addevenstdialog() {
        sp_tinchi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tinchi = position + 1;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                hp = tinchi*400000;
                tv_hp.setText("Học Phi: "+decimalFormat.format(hp)+"Đ");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_namhoc_add.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Semester semester = (Semester) parent.getItemAtPosition(position);
                namhoc = semester.getNamhoc();
                hocki = semester.getHocki();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //
        sp_tengv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              Teacher teacher = (Teacher) parent.getItemAtPosition(position);
                tengv = teacher.getTengv();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (tem == true){
            //Log.e("tem",String.valueOf(tem));
            btn_nhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mamh = ed_mamh.getText().toString().trim();
                    tenmh = ed_monh.getText().toString().trim();
                    //tengv = ed_mgv.getText().toString().trim();


                    if (mamh.equals("") || mamh.equals("") || tenmh.equals("") ) {
                        Toast.makeText(getContext(), "ko de trong", Toast.LENGTH_SHORT).show();
                    } else {
                        registerCourse_viewModel.themmonadmin(khoa, manganh, namhoc, hocki, mamh, tenmh, tengv, tinchi,hp).observe(getActivity(), semester_model -> {
                            if (semester_model.isSuccess()) {
                                LoadList(namhoc, hocki);
                                alertDialog.dismiss();
                            } else {
                                Log.e("null", "that bai");
                            }
                        });
                    }

                }
            });
        }


    }

    @Override
    public void OnClick(Semester semester) {
        OpenDialog();
        btn_nhap.setText("Update");
        ed_mamh.setText(semester.getMamh());
        ed_monh.setText(semester.getTenmh());
        //ed_mgv.setText(""+semester.getMgv());
        //
        int position_namhoc = list_namhoc.indexOf(semester.getNamhoc());
        int position_tinchi = list.indexOf(semester.getTinchi());

        sp_tinchi.setSelection(position_tinchi);
        sp_namhoc_add.setSelection(position_namhoc);

        if (tem == false){
            Log.e("tem",String.valueOf(tem));
            btn_nhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mamh = ed_mamh.getText().toString().trim();
                    tenmh = ed_monh.getText().toString().trim();
                   // mgv = ed_mgv.getText().toString().trim();

                    registerCourse_viewModel.updatemonadmin(semester.getId(),khoa, manganh, namhoc, hocki, mamh, tenmh, tengv, tinchi,hp).observe(getActivity(), semester_model -> {
                        if (semester_model.isSuccess()) {
                            LoadList(namhoc, hocki);
                            alertDialog.dismiss();
                        } else {
                            Log.e("null", "that bai");
                        }
                    });
                }
            });
        }

    }

}