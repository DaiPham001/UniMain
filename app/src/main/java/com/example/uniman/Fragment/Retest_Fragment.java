package com.example.uniman.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.uniman.Adapter.Adapter_Retest1;
import com.example.uniman.Adapter.Adapter_Retest2;
import com.example.uniman.Model.Retest;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Retest_ViewModel;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Retest_Fragment extends Fragment implements Adapter_Retest1.OnClick {

    private RadioGroup radioGroup;
    private RecyclerView rcv_tl, rcv_ddk;
    private Button btn_dk;
    private TextView tv_null1, tv_null2;
    private String lop, tenmh, msv;
    private int tc;

    Calendar calendar=Calendar.getInstance();
    // format cách hiển thị ngày/tháng/năm
    SimpleDateFormat sdfD=new SimpleDateFormat("dd/MM/yyyy");
    private String date;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_retest_, container, false);
        addcontroll(v);
        addevenst();
        Loatlist_dktl();
        date = sdfD.format(calendar.getTime());

        // Now you have currentDateTime as a string, you can use it as needed
        Log.d("CurrentDateTime", date);
        return v;
    }

    private void addcontroll(View v) {
        tv_null1 = v.findViewById(R.id.tv_nullt1);
        tv_null2 = v.findViewById(R.id.tv_nullt2);
        btn_dk = v.findViewById(R.id.btn_dktl);
        radioGroup = v.findViewById(R.id.radiogroup);
        rcv_tl = v.findViewById(R.id.rcv_tl);
        rcv_ddk = v.findViewById(R.id.rcv_ddk);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        rcv_tl.setLayoutManager(linearLayoutManager1);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        rcv_ddk.setLayoutManager(linearLayoutManager2);
        //
        //getDate();
    }

    private void addevenst() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_tl) {
                    Loadtlist_tl();
                } else if (checkedId == R.id.radiotct) {
                    Loatlist_tct();
                }
            }
        });


        //
        btn_dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retest_ViewModel retest_viewModel = new ViewModelProvider(getActivity()).get(Retest_ViewModel.class);
                retest_viewModel.themthilai_sv(msv, lop, tenmh, tc, 100000, date).observe(getActivity(), retest_model -> {
                    if (retest_model.isSuccess()) {
                        dialog();
                        Loadtlist_tl();
                    } else {
                        Log.e("null", "null");
                    }
                });

            }
        });
    }

    // thi lại
    private void Loadtlist_tl() {
        Retest_ViewModel retest_viewModel = new ViewModelProvider(getActivity()).get(Retest_ViewModel.class);
        retest_viewModel.getthilai(Utils.user.getMa()).observe(getActivity(), retest_model -> {
            if (retest_model.isSuccess()) {
                Adapter_Retest1 adapter_retest = new Adapter_Retest1(getContext(), retest_model.getResult(), this);
                rcv_tl.setAdapter(adapter_retest);
            } else {
                rcv_tl.setVisibility(View.GONE);
                tv_null1.setVisibility(View.VISIBLE);
            }
        });
    }


//    // thi cải thiện
    private void Loatlist_tct() {
        Retest_ViewModel retest_viewModel = new ViewModelProvider(getActivity()).get(Retest_ViewModel.class);
        retest_viewModel.getthicaithien(Utils.user.getMa()).observe(getActivity(), retest_model -> {
            if (retest_model.isSuccess()) {
                Adapter_Retest1 adapter_retest = new Adapter_Retest1(getContext(), retest_model.getResult(), this);
                rcv_tl.setAdapter(adapter_retest);
            } else {
                rcv_tl.setVisibility(View.GONE);
                tv_null1.setVisibility(View.VISIBLE);
            }
        });
    }


    // cac môn dan ky
    private void Loatlist_dktl() {
        Retest_ViewModel retest_viewModel = new ViewModelProvider(getActivity()).get(Retest_ViewModel.class);
        retest_viewModel.getmonthilai(Utils.user.getMa()).observe(getActivity(), retest_model -> {
            if (retest_model.isSuccess()) {
                if (retest_model != null){
                    Adapter_Retest2 adapter_retest2 = new Adapter_Retest2(getContext(), retest_model.getResult());
                    rcv_ddk.setAdapter(adapter_retest2);
                }else {
                    rcv_ddk.setVisibility(View.GONE);
                    tv_null2.setVisibility(View.VISIBLE);
                }
            } else {
                Log.e("null","null");
//                rcv_ddk.setVisibility(View.GONE);
//                tv_null2.setVisibility(View.VISIBLE);
            }
        });
    }

    private void dialog() {
        // Xây dựng dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn đã đăng ký thành công!");

        // Thêm button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Loatlist_dktl();
                // Xử lý sự kiện khi button được click
                dialog.dismiss(); // Đóng dialog
            }
        });

        // Hiển thị dialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void getDate(){
        // Lấy ngày tháng năm hiện tại
        // Get current date and time



    }

    @Override
    public void OnClick_lt(Retest retest) {
        msv = retest.getMsv();
        lop = retest.getLophp();
        tenmh = retest.getTenmh();
        tc = retest.getTc();
    }

    @Override
    public void onResume() {
        super.onResume();
        Loatlist_dktl();
    }
}