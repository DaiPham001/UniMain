package com.example.uniman.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.uniman.Model.User;
import com.example.uniman.R;
import com.example.uniman.ViewModel.User_ViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class RegisterFragment extends Fragment {
    FirebaseAuth firebaseAuth;


    private EditText ed_madk,ed_passdk1,ed_passdk2;
    private Spinner spiner_dktk;
    private Button btn_dktk,btn_huy;
    String madk,passdk1,passdk2;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter adapter;
    int role;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_register, container, false);
        addcontroll(view);
        addevenst();
        return view;
    }

    private void addcontroll(View v) {
        ed_madk = v.findViewById(R.id.ed_madk);
        ed_passdk1 = v.findViewById(R.id.ed_passdk1);
        ed_passdk2 = v.findViewById(R.id.ed_passdk2);
        btn_dktk = v.findViewById(R.id.btn_dktk);
        btn_huy = v.findViewById(R.id.btn_huy_dk);
        spiner_dktk = v.findViewById(R.id.spiner_dktk);
        Loadspiner_dktk();
    }

    private void addevenst() {
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed_madk.setText("");
                ed_passdk1.setText("");
                ed_passdk2.setText("");
            }
        });
        //
        spiner_dktk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                role = position;
                if (role == 0){
                    role = 2;
                }else if (role == 1){
                    role = 1;
                }
                Log.e("role",String.valueOf(role));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //


        btn_dktk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String madk = ed_madk.getText().toString().trim();
                String passdk1 = ed_passdk1.getText().toString().trim();
                String passdk2 = ed_passdk2.getText().toString().trim();
                if (madk.equals("") || passdk2.equals("") || passdk1.equals("")) {
                    Toast.makeText(getContext(), "Yêu cầu nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (passdk1.equals(passdk2)) {
                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.createUserWithEmailAndPassword(madk, passdk1)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = firebaseAuth.getCurrentUser();
                                            Log.e("tk", madk);
                                            Log.e("mk", passdk1);
                                            if (user != null) {
                                                register(madk, passdk1, role, user.getUid());
                                            }
                                        } else {
                                            Exception exception = task.getException();
                                            if (exception != null) {
                                                Log.e("RegisterError", "Error during registration", exception);
                                                handleRegisterError(exception);
                                            } else {
                                                Toast.makeText(getContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(getContext(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
    private void handleRegisterError(Exception exception) {
        if (exception instanceof FirebaseAuthWeakPasswordException) {
            Toast.makeText(getContext(), "Mật khẩu phải có ít nhất 6 ký tự", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(getContext(), "Email không hợp lệ", Toast.LENGTH_SHORT).show();
        } else if (exception instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(getContext(), "Email đã tồn tại", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Đăng ký thất bại: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void register(String ma,String pass,int role,String uid){
        User_ViewModel user_viewModel = new ViewModelProvider(getActivity()).get(User_ViewModel.class);
        user_viewModel.registter(ma,pass,role,uid).observe(getActivity(),user_model -> {
            if (user_model.isSuccess()){
                Toast.makeText(getContext(), "Đăng thành công", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getContext(), "Đăng thât bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Loadspiner_dktk() {
        //list.add("");
        list.add("Giảng viên");
        list.add("Sinh viên");
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_expandable_list_item_1,list);
        spiner_dktk.setAdapter(adapter);
    }
}