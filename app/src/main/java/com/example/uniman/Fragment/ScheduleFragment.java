package com.example.uniman.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Adapter.Schedule_Adapter;
import com.example.uniman.Model.Schedule;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Schedule_ViewModel;
import com.example.uniman.ViewModel.Teacher_ViewModel;

import java.util.ArrayList;


public class ScheduleFragment extends Fragment implements Schedule_Adapter.OnClick_Schedule {

    private RecyclerView rcv_sl;
    private Schedule_Adapter schedule_adapter;
    private Schedule_ViewModel schedule_viewModel;
    //
    private Spinner spinner;
    private ArrayList<String> daysOfWeek;
    String selectedDay;
    //
    private TextView tv_null;
    private AlertDialog alertDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_schedule, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addcontroll(View view) {
        tv_null = view.findViewById(R.id.tv_null);
        rcv_sl = view.findViewById(R.id.rcv_sl);
        spinner = view.findViewById(R.id.spiner);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_sl.setLayoutManager(layoutManager);
        loadData();
    }



    @SuppressLint("FragmentLiveDataObserve")
    private void Loading() {
        Log.e("u",Utils.user.getMa());
        schedule_viewModel = new ViewModelProvider(ScheduleFragment.this).get(Schedule_ViewModel.class);
        schedule_viewModel.schedule(Utils.user.getMa(), selectedDay).observe(ScheduleFragment.this, schedule_model -> {
            if (schedule_model.isSuccess()) {
                if (schedule_model.getResult().size() > 0) {
                    boolean hasMatchingSchedule = false;
                    for (int i = 0; i < schedule_model.getResult().size(); i++) {
                        if (selectedDay.equalsIgnoreCase(schedule_model.getResult().get(i).getThu())) {
                            hasMatchingSchedule = true;
                            break;
                        }
                    }
                    if (hasMatchingSchedule) {
                        schedule_adapter = new Schedule_Adapter(getContext(), schedule_model.getResult());
                        rcv_sl.setAdapter(schedule_adapter);
                        tv_null.setVisibility(View.GONE);
                        rcv_sl.setVisibility(View.VISIBLE);
                    } else {
                        tv_null.setVisibility(View.VISIBLE);
                        rcv_sl.setVisibility(View.GONE);
                    }
                } else {
                    tv_null.setVisibility(View.VISIBLE);
                    rcv_sl.setVisibility(View.GONE);
                }
            } else {
                tv_null.setVisibility(View.VISIBLE);
                rcv_sl.setVisibility(View.GONE);
            }
        });
    }


    // loaddataspiner
    private void loadData() {
        daysOfWeek = new ArrayList<>();
        daysOfWeek.add("Lịch học");
        daysOfWeek.add("Thứ 2");
        daysOfWeek.add("Thứ 3");
        daysOfWeek.add("Thứ 4");
        daysOfWeek.add("Thứ 5");
        daysOfWeek.add("Thứ 6");
        daysOfWeek.add("Thứ 7");
        daysOfWeek.add("Chủ nhật");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, daysOfWeek);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);
    }

    // xử lý sự kiện
    private void addevenst() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDay = (String) parent.getItemAtPosition(position);
                if (selectedDay != null && !selectedDay.isEmpty()) {

                    if (Utils.user.getRole() == 1) {// nêu là sinh viên hiển thi
                        Loading();
                    }else if (Utils.user.getRole() == 2) {// nêu là giảng viên hiển thị
                     Loadinggv();
                    }

                    //Toast.makeText(getContext(), selectedDay, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void Loadinggv() {
        Teacher_ViewModel teacher_viewModel = new ViewModelProvider(getActivity()).get(Teacher_ViewModel.class);
        teacher_viewModel.lectureschedule(Utils.teacher.getMauser(), selectedDay).observe(getActivity(), schedule_model -> {
            if (schedule_model.isSuccess()) {
                if (schedule_model.getResult().size() > 0) {
                    boolean hasMatchingSchedule = false;
                    for (int i = 0; i < schedule_model.getResult().size(); i++) {
                        if (selectedDay.equalsIgnoreCase(schedule_model.getResult().get(i).getThu())) {
                            hasMatchingSchedule = true;
                            break;
                        }
                    }
                    if (hasMatchingSchedule) {
                        schedule_adapter = new Schedule_Adapter(getContext(), schedule_model.getResult(),this);
                        rcv_sl.setAdapter(schedule_adapter);
                        tv_null.setVisibility(View.GONE);
                        rcv_sl.setVisibility(View.VISIBLE);
                    } else {
                        tv_null.setVisibility(View.VISIBLE);
                        rcv_sl.setVisibility(View.GONE);
                    }
                } else {
                    tv_null.setVisibility(View.VISIBLE);
                    rcv_sl.setVisibility(View.GONE);
                }
            } else {
                tv_null.setVisibility(View.VISIBLE);
                rcv_sl.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onclickSchedule(Schedule schedule) {
        // set dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.cumtom_dialog_schedule, null);
        builder.setView(v);
        // hiển thị dialog
        alertDialog = builder.create();
        alertDialog.setCancelable(false);// ko cho user thoát
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// bo góc
        alertDialog.show();
        Button btnhuy = v.findViewById(R.id.fhf);


        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        //Toast.makeText(getContext(), "đc", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void oncpauseSchedule(Schedule schedule) {
        // Lấy trạng thái hiện tại của schedule
        int currentTrangThai = schedule.getTrangthai();
        //Log.e("trangthaicu",String.valueOf(currentTrangThai));
        // Chuyển đổi trạng thái
        int newTrangThai = (currentTrangThai == 1) ? 0 : 1;
        ////schedule.setTrangthai(newTrangThai);
        //Log.e("trangthai",String.valueOf(schedule.getTrangthai()));

        //
        //api
        Schedule_ViewModel schedule_viewModel = new ViewModelProvider(getActivity()).get(Schedule_ViewModel.class);
        schedule_viewModel.updateschedule(Utils.teacher.getMauser(), schedule.getId(), newTrangThai).observe(getActivity(), schedule_model -> {
            if (schedule_model.isSuccess()){
                //Toast.makeText(getContext(), "đc", Toast.LENGTH_SHORT).show();
                Loadinggv();
            }else {
                Toast.makeText(getContext(), "that bại", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        Loading();
        Loadinggv();
    }

}
