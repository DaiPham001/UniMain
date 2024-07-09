package com.example.uniman.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniman.Activity.MainActivity;
import com.example.uniman.Adapter.Adapter_StudentList;
import com.example.uniman.Model.Student;
import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.Search_ViewModel;
import com.example.uniman.ViewModel.Student_ViewModel;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    private SearchView searchView;
    private Student_ViewModel student_viewModel;
    private Adapter_StudentList adapter_studentList;
    private RecyclerView rcv_srearch;
    private ImageView img_back;
    private ArrayList<Student> list;
    private Search_ViewModel search_viewModel;
    private TextView tv_null;
    MainActivity mainActivity;
    String tenlop,manganh,tennganh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_search, container, false);
        addcontroll(view);
        Loadlist();
        addevenst();
        return view;
    }

    private void addcontroll(View view) {
        list = new ArrayList<>();
        searchView = view.findViewById(R.id.search);
        img_back = view.findViewById(R.id.img_back);
        rcv_srearch = view.findViewById(R.id.rcv_srearch);
        tv_null = view.findViewById(R.id.tv_nulls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_srearch.setLayoutManager(linearLayoutManager);
        mainActivity = (MainActivity) getActivity();
        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);
    }

    @SuppressLint("FragmentLiveDataObserve")
    private void Loadlist() {
        if (Utils.user.getRole() == 3){
            // nhận dũ liệu tù studentfragment
            tenlop = getArguments().getString("tenlop");
            manganh = getArguments().getString("manganh");
            tennganh = getArguments().getString("tennganh");
        }else {
            tenlop = getArguments().getString("tenlop");
        }

//        Log.e("tennn",manganh);
//        Log.e("tennn",tennganh);
        student_viewModel = new ViewModelProvider(SearchFragment.this).get(Student_ViewModel.class);
        student_viewModel.Studentlist(tenlop).observe(getActivity(), student_model -> {
            if (student_model.isSuccess()) {
                adapter_studentList = new Adapter_StudentList(getContext(), student_model.getResult());
                rcv_srearch.setAdapter(adapter_studentList);
                //list = student_model.getResult();
            } else {
                Toast.makeText(mainActivity, "Thât bại", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addevenst() {
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new StudentListFragment();
                Bundle bundle = new Bundle();
                if (Utils.user.getRole() == 3){
                    bundle.putString("ten", tenlop);
                    bundle.putString("manganhs", manganh);
                    bundle.putString("tennganhs", tennganh);
                }else {
                    //Log.e("tenk",tenlop);
                    bundle.putString("tenk", tenlop);
                }
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flcontent, fragment)
                        .addToBackStack(null)
                        .commit();
                getActivity().getSupportFragmentManager().beginTransaction().remove(SearchFragment.this).commit();
            }
        });

        //

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Kiểm tra độ dài chuỗi
                if (newText.length() == 0) {
                    // Nếu chuỗi rỗng, làm gì đó (ví dụ: xóa dữ liệu)
                    list.clear();
                    tv_null.setVisibility(View.GONE);
                    adapter_studentList = new Adapter_StudentList(getContext(),list);
                    rcv_srearch.setAdapter(adapter_studentList);

                } else {
                    // Nếu có chuỗi, làm gì đó (ví dụ: tìm kiếm)
                    getDataSearch(newText);
                    //tv_null.setVisibility(View.GONE);
                }
                return false;
            }

        });
    }


    @Override
    public void onResume() {
        super.onResume();
        Toolbar toolbar = mainActivity.findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

    }

    private void getDataSearch(String s) {
        list.clear();
        search_viewModel = new ViewModelProvider(getActivity()).get(Search_ViewModel.class);
        search_viewModel.Search(s, tenlop).observe(getActivity(), student_model -> {
            if (student_model.isSuccess()) {
                list = student_model.getResult();
                adapter_studentList = new Adapter_StudentList(getContext(), list);
                rcv_srearch.setAdapter(adapter_studentList);
            } else {
               tv_null.setVisibility(View.VISIBLE);
            }
           // Log.e("lo",tenlop);
        });
    }
}