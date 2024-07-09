package com.example.uniman.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.uniman.Adapter.Adapter_ClassGV;
import com.example.uniman.Adapter.Adapter_Classlist;
import com.example.uniman.Model.Schedule;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Schedule_ViewModel;


public class InstructorsClassFragment extends Fragment implements Adapter_ClassGV.Onclick {

    private RecyclerView rcv_classgv;
    private Adapter_ClassGV adapter_classGV;
    private Fragment fragment = null;
    int b;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_instructors_class,container,false);
      addcontroll(view);
      addevenst();
      return view;
    }

    // anh xạ view
    private void addcontroll(View view) {
        rcv_classgv = view.findViewById(R.id.rcv_classgv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_classgv.setLayoutManager(linearLayoutManager);
        Loadlist();

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pc",getActivity().MODE_PRIVATE);
        b = sharedPreferences.getInt("b",-1);
    }

    private void Loadlist() {
        Log.e("mgv",Utils.teacher.getMauser());
        Schedule_ViewModel schedule_viewModel = new ViewModelProvider(getActivity()).get(Schedule_ViewModel.class);
        schedule_viewModel.classlistgv(Utils.teacher.getMauser()).observe(getActivity(), schedule_model -> {
            if (schedule_model.isSuccess()){
                adapter_classGV = new Adapter_ClassGV(getContext(),schedule_model.getResult(),this);
                rcv_classgv.setAdapter(adapter_classGV);
                // dòng kẻ của rcv
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
                rcv_classgv.addItemDecoration(itemDecoration);
            }
        });
    }

    private void addevenst() {
    }

    @Override
    public void onclick(Schedule schedule) {
       // Toast.makeText(getContext(), "đc", Toast.LENGTH_SHORT).show();

        if (b== 1){
            //Log.e("b",String.valueOf(b));
            fragment = new StudentListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("tenlopg",schedule.getLop());
            fragment.setArguments(bundle);
            getFragment();
           // ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Nhập Điểm Lớp: "+ schedule.getLop());
        }else if (b == 2){
           // Log.e("b",String.valueOf(b));
            fragment = new Results_Fragment();
            Bundle bundle = new Bundle();
            bundle.putString("tenlopg",schedule.getLop());
            fragment.setArguments(bundle);
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Nhập Điểm Lớp: "+ schedule.getLop());
        }


    }

    // method khởi tạo fragment
    public void getFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
    }
}