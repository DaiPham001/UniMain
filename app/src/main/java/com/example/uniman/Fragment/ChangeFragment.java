package com.example.uniman.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.uniman.R;
import com.example.uniman.Utils.Utils;
import com.example.uniman.ViewModel.User_ViewModel;


public class ChangeFragment extends Fragment {

    private EditText ed_pass,ed_pass_new,ed_pass_change;
    private Button btn_save,btn_huy;
    private User_ViewModel user_viewModel;
    //
    private Fragment fragment =null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_change,container,false);
       addcontroll(view);
       addevenst();
       return view;
    }

    // ánh xạ view
    private void addcontroll(View view) {
        ed_pass = view.findViewById(R.id.ed_pass_old);
        ed_pass_new = view.findViewById(R.id.ed_pass_new);
        ed_pass_change = view.findViewById(R.id.ed_pass_change);
        btn_huy = view.findViewById(R.id.btn_huy);
        btn_save = view.findViewById(R.id.btn_huy);
        btn_save = view.findViewById(R.id.btn_save);
    }

    // xử lý hoạt động
    private void addevenst() {
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_pass.setText("");
                ed_pass_change.setText("");
                ed_pass_new.setText("");
            }
        });
        //
        btn_save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("FragmentLiveDataObserve")
            @Override
            public void onClick(View v) {
                String pass = ed_pass.getText().toString().trim();
                String passnew = ed_pass_new.getText().toString().trim();
                String passchange = ed_pass_change.getText().toString().trim();

//                Log.e("ma", String.valueOf(Utils.user.getMa()));
//                Log.e("pass", Utils.user.getPassword());
                if (pass.equals("") || passchange.equals("") || passnew.equals("")){
                    Toast.makeText(getContext(), "Yêu cầu nhâp đủ", Toast.LENGTH_SHORT).show();
                }else if (!pass.equals(Utils.user.getPassword())){
                    Toast.makeText(getContext(), "Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show();
                }else if (!passnew.equals(passchange)){
                    Toast.makeText(getContext(), "Mật khẩu mới không trùng", Toast.LENGTH_SHORT).show();
                }else {
                    user_viewModel  = new ViewModelProvider(ChangeFragment.this).get(User_ViewModel.class);
                    user_viewModel.Change(Utils.user.getMa(),passchange).observe(ChangeFragment.this, user_model -> {
                        if (user_model.isSuccess()){
                            Toast.makeText(getContext(), "Thành công", Toast.LENGTH_SHORT).show();
                            fragment = new HomeFragment();
                            getFragment();
                            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Trang Chủ");
                        }else {
                            Toast.makeText(getContext(), "Thất bại", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
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