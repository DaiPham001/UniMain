package com.example.uniman.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uniman.Adapter.Adapter_Classlist;
import com.example.uniman.Listener.Listener_Classlist;
import com.example.uniman.Model.Class;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.ClassList_ViewModel;

public class ClassListFragment extends Fragment implements Listener_Classlist {
    private RecyclerView rcv_class;
    private Adapter_Classlist adapter_classlist;
    private String manganh,tennganh;
    private ClassList_ViewModel classList_viewModel;
    //
    private Fragment fragment =null;
    int a;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_class_list,container,false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addevenst() {

    }

    // ánh xạ view
    private void addcontroll(View view) {
        rcv_class =view.findViewById(R.id.rcv_classlist);
        //
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_class.setLayoutManager(linearLayoutManager);

        // check admin hay giang viên
        if (Utils.user.getRole() == 3){
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("pc",getActivity().MODE_PRIVATE);
            a = sharedPreferences.getInt("p",-1);
            //if ()
            // nhận dữ liệu từ framgentInsustry
            manganh = getArguments().getString("manganh");
            tennganh = getArguments().getString("tennganh");
            Loadlist(manganh);
        }else {
            fragment = new InstructorsClassFragment();
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh sách lớp");
        }

        //Loadlist();
    }

    // list của admin
    private void Loadlist(String a) {

        //Log.e("manganh", manganh);
        classList_viewModel = new ViewModelProvider(getActivity()).get(ClassList_ViewModel.class);
        classList_viewModel.classlist(a).observe(getActivity(), class_model -> {
            if (class_model.isSuccess()){
                adapter_classlist = new Adapter_Classlist(getContext(),class_model.getResult(),this);
                rcv_class.setAdapter(adapter_classlist);
                   // Utils.aClass = class_model.getResult().get(0);
                // dòng kẻ của rcv
                RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),
                        DividerItemDecoration.VERTICAL);
                rcv_class.addItemDecoration(itemDecoration);
            }else {
                Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // thêm
    @Override
    public void OnClickClass(Class aClass) {
        // truyền dữ liệu sang studentfragment
        Log.e("a",String.valueOf(a));
        if (a == 1){
            fragment = new AdminRegisterCourse_Fragment();
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Thêm môn lớp: "+aClass.getTenlop());
        }else if (a== 2){
            Bundle bundle = new Bundle();
            bundle.putString("tenlop",aClass.getTenlop());
            bundle.putString("tennganh",tennganh);
            bundle.putString("manganh",manganh);
            fragment = new StudentListFragment();
            fragment.setArguments(bundle);
            getFragment();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Danh sách lớp: "+aClass.getTenlop());
        }

    }


    // thêm môn học

    // method khởi tạo fragment
    public void getFragment() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flcontent, fragment)
                .addToBackStack(null)
                .commit();
    }
}