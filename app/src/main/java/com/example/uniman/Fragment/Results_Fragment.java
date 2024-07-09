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
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniman.Adapter.Adapter_Results;
import com.example.uniman.Model.Results;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Results_ViewModel;
import com.example.uniman.ViewModel.Retest_ViewModel;

import java.text.DecimalFormat;


public class Results_Fragment extends Fragment implements Adapter_Results.Onclickre {
    private RecyclerView rcv_rs;
    private Adapter_Results adapter_results;
    int role = Utils.user.getRole();
    private TextView tv_hoten, tv;
    private Results_ViewModel results_viewModel;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_results_, container, false);
        addcontroll(view);
        return view;
    }

    //
    private void addcontroll(View view) {
        rcv_rs = view.findViewById(R.id.rcv_rs);
        tv_hoten = view.findViewById(R.id.tv_hoten);
        tv = view.findViewById(R.id.tv_);
        linearLayoutManager = new LinearLayoutManager(getContext());
        results_viewModel = new ViewModelProvider(getActivity()).get(Results_ViewModel.class);
        rcv_rs.setLayoutManager(linearLayoutManager);
        //
        if (role == 1) {
            Loadlist();
            tv_hoten.setVisibility(View.GONE);
            tv.setVisibility(View.GONE);
        } else if (role == 2) {
            Loadlist_napkq();
        }
    }


    private void Loadlist_napkq() {
        String tenlop = getArguments().getString("tenlopg");
        results_viewModel.hienthikq(Utils.teacher.getMauser(), tenlop).observe(getActivity(), results_model -> {
            if (results_model.isSuccess()) {
                adapter_results = new Adapter_Results(getContext(), results_model.getResult(), this);
                rcv_rs.setAdapter(adapter_results);
            }
        });
    }


    private void Loadlist() {
        //
        results_viewModel.results(Utils.user.getMa()).observe(getActivity(), results_model -> {
            if (results_model.isSuccess()) {
                adapter_results = new Adapter_Results(getContext(), results_model.getResult());
                rcv_rs.setAdapter(adapter_results);
            } else {
                Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void oncick(Results results) {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_nhapdiem, null);
        builder.setView(v);
        // hiển thị dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);// ko cho user thoát
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
        alertDialog.show();
        Button btn_huy = v.findViewById(R.id.btn_huyn);
        Button btn_nhan = v.findViewById(R.id.btn_nhap);
        TextView tv_name = v.findViewById(R.id.tv_namen);
        TextView tv_title = v.findViewById(R.id.tv_titlenh);
        tv_title.setText("Môn: " + results.getTenmh());
        tv_name.setText("" + results.getName());

        EditText ed_cc = v.findViewById(R.id.ed_cc);
        EditText ed_d1 = v.findViewById(R.id.ed_d1);
        EditText ed_d2 = v.findViewById(R.id.ed_d2);
        EditText ed_d3 = v.findViewById(R.id.ed_d3);
        EditText ed_dt = v.findViewById(R.id.ed_dt);

        btn_nhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String diemcc = ed_cc.getText().toString().trim();
                String diem1 = ed_d1.getText().toString().trim();
                String diem2 = ed_d2.getText().toString().trim();
                String diem3 = ed_d3.getText().toString().trim();
                String diemck1 = ed_dt.getText().toString().trim();
                int img = 0;
                if (Float.parseFloat(diemcc) < 5) {
                    img = 0;
                } else {
                    img = 1;
                }
                float diemthi, tongdiem, td4;
                diemthi = Float.parseFloat(diemck1);
                float v1 = (float) (((Float.parseFloat(diem1) + Float.parseFloat(diem2) + Float.parseFloat(diem3)) / 3) * 0.3);
                DecimalFormat df = new DecimalFormat("#.##");
                tongdiem = (float) ((Float.parseFloat(diemcc) * 0.1) + v1 + (Float.parseFloat(diemck1) * 0.6));
                df.format(tongdiem);

                // Làm tròn giá trị
                float roundedValue = Math.round((tongdiem / 10) * 4);
                // Làm tròn giá trị
                // Kiểm tra và làm tròn theo yêu cầu
                if (roundedValue > Math.floor(roundedValue) + 0.5) {
                    roundedValue = (float) Math.ceil(roundedValue); // Làm tròn lên
                } else {
                    roundedValue = (float) Math.floor(roundedValue); // Làm tròn xuống
                }
                td4 = Float.parseFloat(df.format(roundedValue));
                //
                String diemchu = "", xeploai = "", ghichu = "";
                if (8.5 <= tongdiem) {
                    diemchu = "A";
                } else if (7 <= tongdiem) {
                    diemchu = "B";
                } else if (5.5 <= tongdiem) {
                    diemchu = "C";
                } else if (4 <= tongdiem) {
                    diemchu = "D";
                } else {
                    diemchu = "F";
                }

                //
                if (diemchu.equals("A")) {
                    xeploai = "Giỏi";
                } else if (diemchu.equals("B")) {
                    xeploai = "Khá";
                } else if (diemchu.equals("C")) {
                    xeploai = "Trung binh";
                } else if (diemchu.equals("D")) {
                    xeploai = "Trung bình yếu";
                } else if (diemchu.equals("F")) {
                    xeploai = "Kém";
                }

                if (xeploai.equals("Kém")){
                    ghichu = "Thi lại";
                }
                if (diemcc.equals("") || diemck1.equals("") || diem1.equals("") || diem2.equals("") || diem3.equals("")) {
                    Toast.makeText(getContext(), "Yêu cầu nhập đủ", Toast.LENGTH_SHORT).show();
                } else {
                    Results_ViewModel results_viewModel = new ViewModelProvider(getActivity()).get(Results_ViewModel.class);
                    String finalXeploai = xeploai;
                    results_viewModel.nhapkq(results.getMsv(), results.getMamh(), Float.parseFloat(diemcc), Float.parseFloat(diem1), Float.parseFloat(diem2)
                            , Float.parseFloat(diem3), img, Float.parseFloat(diemck1), diemthi, tongdiem, td4, diemchu, xeploai,ghichu).observe(getActivity(), results_model -> {
                        if (results_model.isSuccess()) {
                            Toast.makeText(getContext(), "thành công", Toast.LENGTH_SHORT).show();
                            Loadlist_napkq();
                            if (finalXeploai.equals("Kém") || finalXeploai.equals("Trung bình yếu") || finalXeploai.equals("Trung binh") || finalXeploai.equals("Khá")){
                               // Log.e("kq",String.valueOf(results.getDiemck1()));
                                retest1(results.getMsv(),results.getMamh(),results.getLop(),results.getTenmh(),results.getSotc(), finalXeploai,"");
                            }

                            alertDialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    // nêu kem thì tụ động thêm vao bảng
    public void retest1(String msv,String mamh, String lophp,String tenmh, int tc, String kq, String ghichu){
        Retest_ViewModel retest_viewModel = new ViewModelProvider(getActivity()).get(Retest_ViewModel.class);
        retest_viewModel.themthilai(msv,mamh,lophp,tenmh,tc,kq,ghichu).observe(getActivity(),retest_model -> {
            if (retest_model.isSuccess()){
            }else {
                Log.e("null","nullđ");
            }
        });
    }
}