package com.example.uniman.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.uniman.Adapter.Adapter_Yearcourese;
import com.example.uniman.Model.Semester;
import com.example.uniman.R;
import com.example.uniman.ViewModel.RegisterCourse_ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class YearcourseFragment extends Fragment implements Adapter_Yearcourese.OnClick {

   private RecyclerView rcv_yc;
   private FloatingActionButton float_add_khoa;
   //dialog
   private AlertDialog alertDialog;
   private ImageView img_time1,img_time2;
   private EditText ed_nam1,ed_nam2;
   private Button btn_nhap,btn_huy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,   Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_yearcourse,container,false);
        addcontroll(view);
        addevenst();
        LoadList();
        return view;
    }
    private void addcontroll(View v) {
        rcv_yc = v.findViewById(R.id.rcv_yc);
        float_add_khoa = v.findViewById(R.id.float_add_khoa);
    }

    private void addevenst() {
        float_add_khoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private void LoadList(){
        // nhận tù IndustryFragment
        String manganh= getArguments().getString("manganh");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_yc.setLayoutManager(linearLayoutManager);
        RegisterCourse_ViewModel registerCourse_viewModel = new ViewModelProvider(getActivity()).get(RegisterCourse_ViewModel.class);
        registerCourse_viewModel.getkhoahoc(manganh).observe(getActivity(), semester_model -> {
            if(semester_model.isSuccess()){
                Adapter_Yearcourese adapter_yearcourese = new Adapter_Yearcourese(getContext(),semester_model.getResult(),this);
                rcv_yc.setAdapter(adapter_yearcourese);
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
        ed_nam1 = v.findViewById(R.id.ed_nam1);
        ed_nam2 = v.findViewById(R.id.ed_nam2);
        img_time1 = v.findViewById(R.id.img_time1);
        img_time2 = v.findViewById(R.id.img_time2);
        btn_nhap = v.findViewById(R.id.btn_nhap_khoa);
        btn_huy = v.findViewById(R.id.btn_huy_khoa);
    }
    private void addevenstdialog() {
        img_time1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        btn_nhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    @Override
    public void OnclickSemester(Semester semester) {
        String tennganh = getArguments().getString("tennganh");
        String manganh = getArguments().getString("manganh");
        int khoa = semester.getKhoa();
        Bundle bundle = new Bundle();
        bundle.putInt("khoa", khoa);
        bundle.putString("tennganh", tennganh);
        bundle.putString("manganh", manganh);
        Fragment fragment = new AdminRegisterCourse_Fragment();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Đăng Ký Học Phần");
    }
}